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
package io.trino.type;

import io.trino.spi.block.Block;
import io.trino.spi.block.BlockBuilder;
import io.trino.spi.type.DecimalType;
import io.trino.spi.type.SqlDecimal;
import org.junit.jupiter.api.Test;

import static io.trino.spi.type.DecimalType.createDecimalType;
import static org.assertj.core.api.Assertions.assertThat;

public class TestShortDecimalType
        extends AbstractTestType
{
    private static final DecimalType SHORT_DECIMAL_TYPE = createDecimalType(4, 2);

    public TestShortDecimalType()
    {
        super(SHORT_DECIMAL_TYPE, SqlDecimal.class, createTestBlock());
    }

    public static Block createTestBlock()
    {
        BlockBuilder blockBuilder = SHORT_DECIMAL_TYPE.createBlockBuilder(null, 15);
        SHORT_DECIMAL_TYPE.writeLong(blockBuilder, -1234);
        SHORT_DECIMAL_TYPE.writeLong(blockBuilder, -1234);
        SHORT_DECIMAL_TYPE.writeLong(blockBuilder, -1234);
        SHORT_DECIMAL_TYPE.writeLong(blockBuilder, 2321);
        SHORT_DECIMAL_TYPE.writeLong(blockBuilder, 2321);
        SHORT_DECIMAL_TYPE.writeLong(blockBuilder, 2321);
        SHORT_DECIMAL_TYPE.writeLong(blockBuilder, 2321);
        SHORT_DECIMAL_TYPE.writeLong(blockBuilder, 2321);
        SHORT_DECIMAL_TYPE.writeLong(blockBuilder, 3321);
        SHORT_DECIMAL_TYPE.writeLong(blockBuilder, 3321);
        SHORT_DECIMAL_TYPE.writeLong(blockBuilder, 4321);
        return blockBuilder.build();
    }

    @Override
    protected Object getGreaterValue(Object value)
    {
        return ((long) value) + 1;
    }

    @Test
    public void testRange()
    {
        assertThat(type.getRange())
                .isEmpty();
    }

    @Test
    public void testPreviousValue()
    {
        assertThat(type.getPreviousValue(getSampleValue()))
                .isEmpty();
    }

    @Test
    public void testNextValue()
    {
        assertThat(type.getNextValue(getSampleValue()))
                .isEmpty();
    }
}
