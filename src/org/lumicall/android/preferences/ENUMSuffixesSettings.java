package org.lumicall.android.preferences;

import org.lumicall.android.db.ENUMSuffix;
import org.lumicall.android.db.LumicallDataSource;

import java.util.List;

public class ENUMSuffixesSettings extends DBObjectsSettings<ENUMSuffix> {

	@Override
	protected List<ENUMSuffix> getObjects(LumicallDataSource ds) {
		return ds.getENUMSuffixes();
	}

	@Override
	protected String getKeyForIntent() {
		return ENUMSuffix.ENUM_SUFFIX_ID;
	}

	@Override
	protected void deleteObject(LumicallDataSource ds, ENUMSuffix object) {
		ds.deleteENUMSuffix(object);
	}

	@Override
	protected Class getEditorActivity() {
		return ENUMSuffixSettings.class;
	}
}
