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

public class LackOfIceCubesException extends com.zeroc.Ice.UserException
{
    public LackOfIceCubesException()
    {
    }

    public LackOfIceCubesException(Throwable cause)
    {
        super(cause);
    }

    public String ice_id()
    {
        return "::SmartHome::LackOfIceCubesException";
    }

    /** @hidden */
    @Override
    protected void _writeImpl(com.zeroc.Ice.OutputStream ostr_)
    {
        ostr_.startSlice("::SmartHome::LackOfIceCubesException", -1, true);
        ostr_.endSlice();
    }

    /** @hidden */
    @Override
    protected void _readImpl(com.zeroc.Ice.InputStream istr_)
    {
        istr_.startSlice();
        istr_.endSlice();
    }

    /** @hidden */
    public static final long serialVersionUID = -1630129369L;
}