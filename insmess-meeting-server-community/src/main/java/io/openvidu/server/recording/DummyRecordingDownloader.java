/*
 * (C) Copyright 2017-2022 OpenVidu (https://openvidu.io)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.openvidu.server.recording;

import java.io.IOException;
import java.util.Collection;

public class DummyRecordingDownloader implements RecordingDownloader {

	@Override
	public void downloadRecording(Recording recording, Collection<RecorderEndpointWrapper> wrappers, Runnable callback)
			throws IOException {
		// Just immediately run callback function
		callback.run();
	}

	@Override
	public void cancelDownload(String recordingId) {
	}

}
