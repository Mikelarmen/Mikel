//////////////////////////////////////////////////////////////////////////////
//	Copyright 2011 Alex Leffelman
//	
//	Licensed under the Apache License, Version 2.0 (the "License");
//	you may not use this file except in compliance with the License.
//	You may obtain a copy of the License at
//	
//	http://www.apache.org/licenses/LICENSE-2.0
//	
//	Unless required by applicable law or agreed to in writing, software
//	distributed under the License is distributed on an "AS IS" BASIS,
//	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//	See the License for the specific language governing permissions and
//	limitations under the License.
//////////////////////////////////////////////////////////////////////////////

package com.example.rememberingreminding.leff.midi.event.meta;

public class Marker extends TextualMetaEvent
{
    public Marker(long tick, long delta, String marker)
    {
        super(tick, delta, MARKER, marker);
    }

    public void setMarkerName(String name)
    {
        setText(name);
    }

    public String getMarkerName()
    {
        return getText();
    }
}
