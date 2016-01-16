package org.lumicall.android.preferences;

import org.lumicall.android.R;
import org.lumicall.android.db.ENUMSuffix;
import org.lumicall.android.db.LumicallDataSource;

public class ENUMSuffixSettings extends DBObjectSettings<ENUMSuffix> {
	
	@Override
	protected void persistObject(LumicallDataSource ds, ENUMSuffix object) {
		ds.persistENUMSuffix(object);
	}

	@Override
	protected ENUMSuffix createObject() {
		return new ENUMSuffix();
	}

	@Override
	protected ENUMSuffix getObject(LumicallDataSource ds, long id) {
		return ds.getENUMSuffix(id);
	}

	@Override
	protected String getKeyForIntent() {
		return ENUMSuffix.ENUM_SUFFIX_ID;
	}

	@Override
	protected int getResForSettings() {
		return R.xml.enum_suffix_settings;
	}

	@Override
	protected void onChanged() {
		//Receiver.engine(context).updateDNS();
   		//Receiver.engine(this.getBaseContext()).halt();
		//Receiver.engine(this.getBaseContext()).StartEngine();
	}

}
