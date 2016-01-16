package org.lumicall.android.preferences;

import org.lumicall.android.R;
import org.lumicall.android.db.LumicallDataSource;
import org.lumicall.android.db.SIPIdentity;
import org.sipdroid.sipua.ui.Receiver;

public class SIPIdentitySettings extends DBObjectSettings<SIPIdentity> {
	
	@Override
	protected void persistObject(LumicallDataSource ds, SIPIdentity object) {
		ds.persistSIPIdentity(object);
	}

	@Override
	protected SIPIdentity createObject() {
		return new SIPIdentity();
	}

	@Override
	protected SIPIdentity getObject(LumicallDataSource ds, long id) {
		return ds.getSIPIdentity(id);
	}

	@Override
	protected String getKeyForIntent() {
		return SIPIdentity.SIP_IDENTITY_ID;
	}

	@Override
	protected int getResForSettings() {
		return R.xml.sip_identity_settings;
	}

	@Override
	protected void onChanged() {
		//Receiver.engine(context).updateDNS();
   		Receiver.engine(this.getBaseContext()).halt();
		Receiver.engine(this.getBaseContext()).StartEngine();
	}

}
