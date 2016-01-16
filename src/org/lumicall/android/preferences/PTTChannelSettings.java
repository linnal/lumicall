package org.lumicall.android.preferences;

import android.preference.Preference;

import org.lumicall.android.R;
import org.lumicall.android.db.LumicallDataSource;
import org.lumicall.android.db.PTTChannel;
import org.zoolu.sdp.SRTPKeySpec;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Logger;

import de.rtner.security.auth.spi.PBKDF2Engine;
import de.rtner.security.auth.spi.PBKDF2Parameters;

public class PTTChannelSettings extends DBObjectSettings<PTTChannel> {
	
	Logger logger = Logger.getLogger(getClass().getName());
	
	@Override
	protected void persistObject(LumicallDataSource ds, PTTChannel object) {
		ds.persistPTTChannel(object);
	}

	@Override
	protected PTTChannel createObject() {
		return new PTTChannel();
	}

	@Override
	protected PTTChannel getObject(LumicallDataSource ds, long id) {
		return ds.getPTTChannel(id);
	}

	@Override
	protected String getKeyForIntent() {
		return PTTChannel.PTT_CHANNEL_ID;
	}

	@Override
	protected int getResForSettings() {
		return R.xml.ptt_channel_settings;
	}

	@Override
	protected void onChanged() {
	}
	
	final static String KEY_FIELD = "ptt_channel_key";
	
	/**
	 * We intercept this method so we can transform the password into a
	 * key for SRTP, we never store the actual password
	 */
	@Override
	protected boolean setBeanValue(Method m, Preference p, Object newValue)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			SecurityException, NoSuchMethodException {
		String key = p.getKey();
		Object val = newValue;
		if(key.equals(KEY_FIELD)) {
			String password = (String)val;
			if(password.length() == 0)
				val = null;
			else
				val = makeSRTPKey(password);
			logger.info("Setting SRTP master key/salt: " + val);
		}
		return super.setBeanValue(m, p, val);
	}
	
	/**
	 * We intercept this method so that we can hide the key from the prefs screen
	 */
	@Override
	protected void updatePreferenceSummary(Preference p, String value) {
		String _value = value;
		if(p.getKey().equals(KEY_FIELD)) {
			if(_value == null || _value.length() == 0)
				_value = getString(R.string.ptt_channel_key_not_set);
			else
				_value = getString(R.string.ptt_channel_key_set);
		}
		p.setSummary(_value);
	}
	
	// salt for the KDF, not the same as the salt for AES
	final static byte[] KDF_SALT = new byte[] {
		(byte) 0xA0, (byte) 0x23, (byte) 0x80, (byte) 0x5f,
		(byte) 0x33, (byte) 0x9c, (byte) 0x8a, (byte) 0x17
	};
	
	// Use a HMAC to convert password into an AES key and salt in base64
	// suitable for SRTP
	protected String makeSRTPKey(String password) {	
		int iterations = 1000;
        PBKDF2Parameters p = new PBKDF2Parameters(
        		"HmacSHA1", "ISO-8859-1",
                KDF_SALT, iterations);
        PBKDF2Engine e = new PBKDF2Engine(p);
        
        byte[] master = e.deriveKey(password, 128);
		byte[] salt = e.deriveKey(password, 112);
		
		SRTPKeySpec keySpec = new SRTPKeySpec(master, salt);
		return keySpec.toString();
	}

}
