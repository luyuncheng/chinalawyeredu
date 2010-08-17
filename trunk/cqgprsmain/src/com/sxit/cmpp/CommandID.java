/*
 * NAME: com.sxit.cmpp.CommandID.java Company:SXIT
 */
package com.sxit.cmpp;

/**
 * cmppЭ��������Ӧid
 * @author HuaFeng
 * @version 1.0 (2005-3-28 23:06:50)
 */
public interface CommandID {
    /**
     * connect������,sp->ismg
     */
    int CMPP_CONNECT = 1;

    /**
     * connectresp������id,ismg->sp
     */

    int CMPP_CONNECT_REP = 0x80000001;

    /**
     * terminate����,sp->ismg��ismg->sp
     */

    int CMPP_TERMINATE = 2;

    /**
     * terminateresp����,sp->ismg��ismg->sp
     */
    int CMPP_TERMINATE_REP = 0x80000002;

    /**
     * submit����,sp->ismg
     */
    int CMPP_SUBMIT = 4;

    /**
     * submitresp����,ismg->sp
     */
    int CMPP_SUBMIT_REP = 0x80000004;

    /**
     * deliver����,ismg->sp
     */
    int CMPP_DELIVER = 5;

    /**
     * deliverresp����,sp->ismg
     */
    int CMPP_DELIVER_REP = 0x80000005;

    /**
     * query����,sp->ismg
     */
//    int CMPP_QUERY = 6;

    /**
     * queryresp����,ismg->sp
     */
//    int CMPP_QUERY_REP = 0x80000006;

    /**
     * cancel����,sp->ismg
     */
//    int CMPP_CANCEL = 7;

    /**
     * cancelresp����,sp->ismg
     */
//    int CMPP_CANCEL_REP = 0x80000007;

    /**
     * active����,sp->ismg��ismg->sp
     */
    int CMPP_ACTIVE_TEST = 8;

    /**
     * activeresp����,sp->ismg��ismg->sp
     */
    int CMPP_ACTIVE_TEST_REP = 0x80000008;
}