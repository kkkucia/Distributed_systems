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

public interface LightBulbLED extends LightBulb
{
    LedColour setLedColour(LedColour colour, com.zeroc.Ice.Current current)
        throws DeviceIsOffException;

    /** @hidden */
    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::SmartHome::Device",
        "::SmartHome::LightBulb",
        "::SmartHome::LightBulbLED"
    };

    @Override
    default String[] ice_ids(com.zeroc.Ice.Current current)
    {
        return _iceIds;
    }

    @Override
    default String ice_id(com.zeroc.Ice.Current current)
    {
        return ice_staticId();
    }

    static String ice_staticId()
    {
        return "::SmartHome::LightBulbLED";
    }

    /**
     * @hidden
     * @param obj -
     * @param inS -
     * @param current -
     * @return -
     * @throws com.zeroc.Ice.UserException -
    **/
    static java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceD_setLedColour(LightBulbLED obj, final com.zeroc.IceInternal.Incoming inS, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        com.zeroc.Ice.Object._iceCheckMode(com.zeroc.Ice.OperationMode.Idempotent, current.mode);
        com.zeroc.Ice.InputStream istr = inS.startReadParams();
        LedColour iceP_colour;
        iceP_colour = LedColour.ice_read(istr);
        inS.endReadParams();
        LedColour ret = obj.setLedColour(iceP_colour, current);
        com.zeroc.Ice.OutputStream ostr = inS.startWriteParams();
        LedColour.ice_write(ostr, ret);
        inS.endWriteParams(ostr);
        return inS.setResult(ostr);
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "getBrightness",
        "getColour",
        "getMode",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "isDeviceTurnOFF",
        "setBrightness",
        "setLedColour",
        "setMode"
    };

    /** @hidden */
    @Override
    default java.util.concurrent.CompletionStage<com.zeroc.Ice.OutputStream> _iceDispatch(com.zeroc.IceInternal.Incoming in, com.zeroc.Ice.Current current)
        throws com.zeroc.Ice.UserException
    {
        int pos = java.util.Arrays.binarySearch(_iceOps, current.operation);
        if(pos < 0)
        {
            throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
        }

        switch(pos)
        {
            case 0:
            {
                return LightBulb._iceD_getBrightness(this, in, current);
            }
            case 1:
            {
                return LightBulb._iceD_getColour(this, in, current);
            }
            case 2:
            {
                return Device._iceD_getMode(this, in, current);
            }
            case 3:
            {
                return com.zeroc.Ice.Object._iceD_ice_id(this, in, current);
            }
            case 4:
            {
                return com.zeroc.Ice.Object._iceD_ice_ids(this, in, current);
            }
            case 5:
            {
                return com.zeroc.Ice.Object._iceD_ice_isA(this, in, current);
            }
            case 6:
            {
                return com.zeroc.Ice.Object._iceD_ice_ping(this, in, current);
            }
            case 7:
            {
                return Device._iceD_isDeviceTurnOFF(this, in, current);
            }
            case 8:
            {
                return LightBulb._iceD_setBrightness(this, in, current);
            }
            case 9:
            {
                return _iceD_setLedColour(this, in, current);
            }
            case 10:
            {
                return Device._iceD_setMode(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}