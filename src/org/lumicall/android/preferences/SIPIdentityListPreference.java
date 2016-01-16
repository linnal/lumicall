package org.lumicall.android.preferences;

import android.content.Context;
import android.util.AttributeSet;

import org.lumicall.android.db.LumicallDataSource;
import org.lumicall.android.db.SIPIdentity;

import java.util.List;

public class SIPIdentityListPreference extends DBObjectListPreference<SIPIdentity> {
	
	public SIPIdentityListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public SIPIdentityListPreference(Context context) {
		super(context);
	}

	@Override
	protected List<SIPIdentity> getObjects(LumicallDataSource ds) {
		return ds.getSIPIdentities();
	}
}
