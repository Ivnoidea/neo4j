/*
 * Copyright (c) 2002-2018 "Neo4j,"
 * Neo4j Sweden AB [http://neo4j.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.impl.storageengine.impl.recordstorage;

import static org.neo4j.kernel.impl.store.record.AbstractBaseRecord.NO_ID;

class GroupReferenceEncoding
{
    private static final long DIRECT = 0x1000_0000_0000_0000L;

    /**
     * Encode a relationship id as a group reference.
     */
    static long encodeRelationship( long relationshipId )
    {
        return relationshipId | DIRECT | References.FLAG_MARKER;
    }

    /**
     * Check whether a group reference is an encoded relationship id.
     */
    static boolean isRelationship( long groupReference )
    {
        assert groupReference != NO_ID;
        return (groupReference & References.FLAG_MASK) == DIRECT;
    }
}