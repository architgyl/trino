/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.trino.plugin.atop;

import com.google.common.collect.ImmutableMap;
import io.trino.spi.Plugin;
import io.trino.spi.connector.ConnectorFactory;
import io.trino.testing.TestingConnectorContext;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static com.google.common.collect.Iterables.getOnlyElement;
import static io.airlift.testing.Assertions.assertInstanceOf;

public class TestAtopPlugin
{
    @Test
    public void testCreateConnector()
            throws Exception
    {
        Plugin plugin = new AtopPlugin();
        assertInstanceOf(getOnlyElement(plugin.getConnectorFactories()), AtopConnectorFactory.class);

        ConnectorFactory factory = getOnlyElement(plugin.getConnectorFactories());

        Path atopExecutable = Files.createTempFile(null, null);
        factory.create("test", ImmutableMap.of("atop.executable-path", atopExecutable.toString()), new TestingConnectorContext()).shutdown();
    }
}
