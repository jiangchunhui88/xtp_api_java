package com.zts.xtp.demo;

import com.zts.xtp.demo.quote.QuoteDemo;
import com.zts.xtp.demo.trade.TradeDemo;

public class Application {

    private static final String XTP_LIB_FOLDER = "/usr/local/lib";
    private static final String XTP_TRADE_SERVER_IP = "192.168.0.11";
    private static final String XTP_QUOTE_SERVER_IP = "192.168.0.12";
    private static final int XTP_TRADE_SERVER_PORT = 6001;
    private static final int XTP_QUOTE_SERVER_PORT = 6002;
    private static final String ACCOUNT = "test_user";
    private static final String PASSWORD = "123456";
    private static final String TRADE_KEY = "123456adfbcda";
    private static final short CLIENT_ID = 18;
    private static final String DATA_FOLDER = "/var/log/zts/xtp";

    private static TradeDemo tradeDemo;
    private static QuoteDemo quoteDemo;

    private static void loadLibrary(String libFolder) {
        System.load(libFolder + "/libxtptraderapi.so");
        System.load(libFolder + "/libxtpquoteapi.so");
        System.load(libFolder + "/libglog.so");
        System.load(libFolder + "/libtradeplugin.so");
        System.load(libFolder + "/libquoteplugin.so");
    }

    private static boolean init() {
        loadLibrary(XTP_LIB_FOLDER);
        tradeDemo = new TradeDemo(XTP_TRADE_SERVER_IP, XTP_TRADE_SERVER_PORT,
            ACCOUNT, PASSWORD, CLIENT_ID, TRADE_KEY, DATA_FOLDER);

        quoteDemo = new QuoteDemo(XTP_QUOTE_SERVER_IP, XTP_QUOTE_SERVER_PORT,
            ACCOUNT, PASSWORD, CLIENT_ID, DATA_FOLDER);

        return tradeDemo.isLogin() && quoteDemo.isLogin();
    }

    private static void shutdown() {
        if (tradeDemo.isLogin()) {
            tradeDemo.shutdown();
        }

        if (quoteDemo.isLogin()) {
            quoteDemo.shutdown();
        }
    }

    public static void main(String[] args) {
        boolean loginFlag = init();
        if (!loginFlag) {
            return;
        }

        quoteDemo.testSubscribeMarketData();
        tradeDemo.testInsertOrder();

        shutdown();
    }
}
