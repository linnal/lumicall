package org.lumicall.android.preferences;

import org.lumicall.android.db.LumicallDataSource;
import org.lumicall.android.db.PTTChannel;

import java.util.List;

public class PTTChannelsSettings extends DBObjectsSettings<PTTChannel> {

	@Override
	protected List<PTTChannel> getObjects(LumicallDataSource ds) {
		return ds.getPTTChannels();
	}

	@Override
	protected String getKeyForIntent() {
		return PTTChannel.PTT_CHANNEL_ID;
	}

	@Override
	protected void deleteObject(LumicallDataSource ds, PTTChannel object) {
		ds.deletePTTChannel(object);
	}

	@Override
	protected Class getEditorActivity() {
		return PTTChannelSettings.class;
	}
}
