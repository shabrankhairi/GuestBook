/*
 * Copyright 2014-2016 the original author or authors.
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

import org.junit.jupiter.api.Test;

class GuestBookEntryUnitTests {

    @Test
    void rejectsEmptyName() {

        assertThatExceptionOfType(IllegalArgumentException.class)//
                .isThrownBy(() -> new GuestBookEntry("",  "" ,  "Bogor"));
    }

    @Test
    void rejectsEmptyText() {

        assertThatExceptionOfType(IllegalArgumentException.class)//
                .isThrownBy(() -> new GuestBookEntry("Shabran", "",  ""));
    }

    @Test
    void setsCreationDate() {
        assertThat(new GuestBookEntry("Shabran", "",  "Bogor").getDate()).isNotNull();
    }
}
