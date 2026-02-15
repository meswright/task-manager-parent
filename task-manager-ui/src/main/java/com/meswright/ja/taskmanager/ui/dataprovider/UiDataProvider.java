package com.meswright.ja.taskmanager.ui.dataprovider;

import com.meswright.ja.taskmanager.ui.client.BackendApiClient;
import com.meswright.ja.taskmanager.ui.pojo.TestUio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UiDataProvider {

    private final BackendApiClient client;

    public TestUio test() {
        return new TestUio(client.test().time());
    }

}
