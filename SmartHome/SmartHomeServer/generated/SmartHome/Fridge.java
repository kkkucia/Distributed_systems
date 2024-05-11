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

public interface Fridge extends TemperatureSensor
{
    /** @hidden */
    static final String[] _iceIds =
    {
        "::Ice::Object",
        "::SmartHome::Device",
        "::SmartHome::Fridge",
        "::SmartHome::TemperatureSensor"
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
        return "::SmartHome::Fridge";
    }

    /** @hidden */
    final static String[] _iceOps =
    {
        "getMode",
        "getTemperature",
        "getTemperatureSensorLimits",
        "ice_id",
        "ice_ids",
        "ice_isA",
        "ice_ping",
        "isDeviceTurnOFF",
        "setMode",
        "setTemperature",
        "setTemperatureLimits"
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
                return Device._iceD_getMode(this, in, current);
            }
            case 1:
            {
                return TemperatureSensor._iceD_getTemperature(this, in, current);
            }
            case 2:
            {
                return TemperatureSensor._iceD_getTemperatureSensorLimits(this, in, current);
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
                return Device._iceD_setMode(this, in, current);
            }
            case 9:
            {
                return TemperatureSensor._iceD_setTemperature(this, in, current);
            }
            case 10:
            {
                return TemperatureSensor._iceD_setTemperatureLimits(this, in, current);
            }
        }

        assert(false);
        throw new com.zeroc.Ice.OperationNotExistException(current.id, current.facet, current.operation);
    }
}