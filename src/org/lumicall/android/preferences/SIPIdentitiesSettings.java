package org.lumicall.android.preferences;

import org.lumicall.android.db.LumicallDataSource;
import org.lumicall.android.db.SIPIdentity;

import java.util.List;

public class SIPIdentitiesSettings extends DBObjectsSettings<SIPIdentity> {

	@Override
	protected List<SIPIdentity> getObjects(LumicallDataSource ds) {
		return ds.getSIPIdentities();
	}

	@Override
	protected String getKeyForIntent() {
		return SIPIdentity.SIP_IDENTITY_ID;
	}

	@Override
	protected void deleteObject(LumicallDataSource ds, SIPIdentity object) {
		ds.deleteSIPIdentity(object);
	}

	@Override
	protected Class getEditorActivity() {
		return SIPIdentitySettings.class;
	}
}
