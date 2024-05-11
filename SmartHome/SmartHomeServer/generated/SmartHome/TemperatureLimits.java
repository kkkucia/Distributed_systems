//
// Copyright (c) ZeroC, Inc. All rights reserved.
//
//
// Ice version 3.7.10
//
// <auto-generated>
//
// Generated from file `server.ice'
//
// Warning: do not edit this file.
//
// </auto-generated>
//

package SmartHome;

public class TemperatureLimits implements java.lang.Cloneable,
                                          java.io.Serializable
{
    public float lowerLimit;

    public float upperLimit;

    public TemperatureLimits()
    {
    }

    public TemperatureLimits(float lowerLimit, float upperLimit)
    {
        this.lowerLimit = lowerLimit;
        this.upperLimit = upperLimit;
    }

    public boolean equals(java.lang.Object rhs)
    {
        if(this == rhs)
        {
            return true;
        }
        TemperatureLimits r = null;
        if(rhs instanceof TemperatureLimits)
        {
            r = (TemperatureLimits)rhs;
        }

        if(r != null)
        {
            if(this.lowerLimit != r.lowerLimit)
            {
                return false;
            }
            if(this.upperLimit != r.upperLimit)
            {
                return false;
            }

            return true;
        }

        return false;
    }

    public int hashCode()
    {
        int h_ = 5381;
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, "::SmartHome::TemperatureLimits");
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, lowerLimit);
        h_ = com.zeroc.IceInternal.HashUtil.hashAdd(h_, upperLimit);
        return h_;
    }

    public TemperatureLimits clone()
    {
        TemperatureLimits c = null;
        try
        {
            c = (TemperatureLimits)super.clone();
        }
        catch(CloneNotSupportedException ex)
        {
            assert false; // impossible
        }
        return c;
    }

    public void ice_writeMembers(com.zeroc.Ice.OutputStream ostr)
    {
        ostr.writeFloat(this.lowerLimit);
        ostr.writeFloat(this.upperLimit);
    }

    public void ice_readMembers(com.zeroc.Ice.InputStream istr)
    {
        this.lowerLimit = istr.readFloat();
        this.upperLimit = istr.readFloat();
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, TemperatureLimits v)
    {
        if(v == null)
        {
            _nullMarshalValue.ice_writeMembers(ostr);
        }
        else
        {
            v.ice_writeMembers(ostr);
        }
    }

    static public TemperatureLimits ice_read(com.zeroc.Ice.InputStream istr)
    {
        TemperatureLimits v = new TemperatureLimits();
        v.ice_readMembers(istr);
        return v;
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, java.util.Optional<TemperatureLimits> v)
    {
        if(v != null && v.isPresent())
        {
            ice_write(ostr, tag, v.get());
        }
    }

    static public void ice_write(com.zeroc.Ice.OutputStream ostr, int tag, TemperatureLimits v)
    {
        if(ostr.writeOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            ostr.writeSize(8);
            ice_write(ostr, v);
        }
    }

    static public java.util.Optional<TemperatureLimits> ice_read(com.zeroc.Ice.InputStream istr, int tag)
    {
        if(istr.readOptional(tag, com.zeroc.Ice.OptionalFormat.VSize))
        {
            istr.skipSize();
            return java.util.Optional.of(TemperatureLimits.ice_read(istr));
        }
        else
        {
            return java.util.Optional.empty();
        }
    }

    private static final TemperatureLimits _nullMarshalValue = new TemperatureLimits();

    /** @hidden */
    public static final long serialVersionUID = -1362069482L;
}
