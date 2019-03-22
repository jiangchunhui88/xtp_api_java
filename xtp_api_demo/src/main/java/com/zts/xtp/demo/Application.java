package com.zts.xtp.demo;

import com.zts.xtp.demo.quote.QuoteDemo;
import com.zts.xtp.demo.trade.TradeDemo;

public class Application {

    private static final String XTP_TRADE_SERVER_IP = "120.27.164.69";//xtp交易server的ip
    private static final int XTP_TRADE_SERVER_PORT = 6001;//xtp交易server的端口
    private static final String TRADE_KEY = "b8aa7173bba3470e390d787219b2112e";//xtp交易serverkey
    private static final short CLIENT_ID = 18;//xtp允许的clientid
    private static final String XTP_QUOTE_SERVER_IP = "120.27.164.138";//xtp行情server的ip
    private static final int XTP_QUOTE_SERVER_PORT = 6002;//xtp行情server的端口
    private static final String ACCOUNT = "xxxxxx";//xtp资金账号
    private static final String PASSWORD = "xxxxxx";//xtp密码
    private static final String DATA_FOLDER = "/var/log/zts/xtp";//java api输出日志的本地目录

    private static TradeDemo tradeDemo;
    private static QuoteDemo quoteDemo;


    private static boolean init() {
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
