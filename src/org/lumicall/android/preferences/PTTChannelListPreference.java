package org.lumicall.android.preferences;

import android.content.Context;
import android.util.AttributeSet;

import org.lumicall.android.db.LumicallDataSource;
import org.lumicall.android.db.PTTChannel;

import java.util.List;

public class PTTChannelListPreference extends DBObjectListPreference<PTTChannel> {
	
	public PTTChannelListPreference(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public PTTChannelListPreference(Context context) {
		super(context);
	}
	
	@Override
	protected List<PTTChannel> getObjects(LumicallDataSource ds) {
		return ds.getPTTChannels();
	}
}
