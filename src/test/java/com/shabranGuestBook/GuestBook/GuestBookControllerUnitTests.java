/*
 * Copyright 2016-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.shabranGuestBook.GuestBook;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Streamable;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
class GuestBookControllerUnitTests {

    @Mock GuestBookRepository guestbook;

    @Test
    void populatesModelForGuestbook() {

        GuestBookEntry entry = new GuestBookEntry("Shabran", "", "");
        doReturn(Streamable.of(entry)).when(guestbook).findAll();

        Model model = new ExtendedModelMap();

        GuestBookController controller = new GuestBookController(guestbook);
        String viewName = controller.guestBook(model, new GuestBookForm(null, null, null));

        assertThat(viewName).isEqualTo("guestbook");
        assertThat(model.asMap().get("entries")).isInstanceOf(Iterable.class);
        assertThat(model.asMap().get("form")).isNotNull();

        verify(guestbook, times(1)).findAll();
    }
}
