package com.zts.xtp.quote.api;


import com.zts.xtp.common.model.ErrorMessage;
import com.zts.xtp.quote.model.response.DepthMarketDataResponse;
import com.zts.xtp.quote.model.response.DepthMarketDataExResponse;
import com.zts.xtp.quote.model.response.OrderBookResponse;
import com.zts.xtp.quote.model.response.SpecificTickerResponse;
import com.zts.xtp.quote.model.response.TickByTickResponse;
import com.zts.xtp.quote.model.response.TickerInfoResponse;
import com.zts.xtp.quote.model.response.TickerPriceInfoResponse;
import com.zts.xtp.quote.spi.QuoteSpi;


public class QuoteApi {
    /**
     *  行情回调接口，为了解耦行情插件与后端逻辑，后端通过构造注入来插入具体的回调实现
     */
    private QuoteSpi quoteSpi;
    private long nativeHandle;

    public QuoteApi(QuoteSpi quoteSpi) {
        this.quoteSpi = quoteSpi;
    }

    //======================native functions======================================

    /**
     *  初始化QuoteApi
     * @param proxyClientId client_id （必须输入）用于区分同一用户的不同客户端，由用户自定义
     * @param xtpDataFolder 存贮订阅信息文件的目录，请设定一个有可写权限的真实存在的路径
     */
    public native void connect(short proxyClientId, String xtpDataFolder);

    /**
     * 断开于xtp连接
     */
    public native void disconnect();

    /**
     *
     * 此函数为同步阻塞式，不需要异步等待登录成功，当函数返回即可进行后续操作，此api只能有一个连接
     *@return 登录是否成功，“0”表示登录成功，“-1”表示连接服务器出错，此时用户可以调用GetApiLastError()来获取错误代码，“-2”表示已存在连接，不允许重复登录，如果需要重连，请先logout，“-3”表示输入有错误
     * @param ip 服务器ip地址，类似“127.0.0.1”
     * @param port 服务器端口号
     * @param user 登陆用户名
     * @param password 登陆密码
     * @param protocol “1”代表TCP，“2”代表UDP
     */
    public native int login(String ip, int port, String user, String password, int protocol);

    /**
     * 登出
     * @return 登出是否成功，“0”表示登出成功，“-1”表示登出失败
     */
    public native int logout();

    /**
     *  此函数必须在Login之前调用
     * @param interval interval 心跳检测时间间隔，单位为秒
     */
    public native void setHeartBeatInterval(int interval);
    
    /**
     *  此函数必须在Login之前调用
     * @param buff_size buff_size 接口接受行情的缓存，单位为M
     */
    public native void setUDPBufferSize(int buff_size);

    /**
     *  订阅行情，包括股票、指数和期权。可以一次性订阅同一证券交易所的多个合约，无论用户因为何种问题需要重新登录行情服务器，都需要重新订阅行情
     * @param ticker 合约ID数组
     * @param count 要订阅/退订行情的合约个数
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int subscribeMarketData(String[] ticker, int count, int exchangeId);

    /**
     *  退订行情，包括股票、指数和期权。可以一次性取消订阅同一证券交易所的多个合约，需要与订阅行情接口配套使用
     * @param ticker 合约ID数组
     * @param count 要订阅/退订行情的合约个数
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int unSubscribeMarketData(String[] ticker, int count, int exchangeId);

    /**
     *  订阅行情订单簿，包括股票、指数和期权。
     * @param ticker 合约ID数组
     * @param count 要订阅/退订行情的合约个数
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int subscribeOrderBook(String[] ticker, int count, int exchangeId);

    /**
     *  退订行情订单簿，包括股票、指数和期权。
     * @param ticker 合约ID数组
     * @param count 要订阅/退订行情的合约个数
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int unSubscribeOrderBook(String[] ticker, int count, int exchangeId);

    /**
     *  订阅逐笔行情，包括股票、指数和期权。
     * @param ticker 合约ID数组
     * @param count 要订阅/退订行情的合约个数
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int subscribeTickByTick(String[] ticker, int count, int exchangeId);

    /**
     *  退订逐笔行情，包括股票、指数和期权。
     * @param ticker 合约ID数组
     * @param count 要订阅/退订行情的合约个数
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int unSubscribeTickByTick(String[] ticker, int count, int exchangeId);

    /**
     *  订阅全市场的股票行情
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int subscribeAllMarketData(int exchangeId);

    /**
     *  退订全市场的股票行情
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int unSubscribeAllMarketData(int exchangeId);

    /**
     *  订阅全市场的股票行情订单簿
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int subscribeAllOrderBook(int exchangeId);

    /**
     *  退订全市场的股票行情订单簿
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int unSubscribeAllOrderBook(int exchangeId);

    /**
     *  订阅全市场的股票逐笔行情
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int subscribeAllTickByTick(int exchangeId);

    /**
     *  退订全市场的股票逐笔行情
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int unSubscribeAllTickByTick(int exchangeId);

    /**
     *  获取当前交易日可交易合约
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int queryAllTickers(int exchangeId);

    /**
     *  获取合约的最新价格信息
     * @param ticker 合约ID数组
     * @param count 要订阅/退订行情的合约个数
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int queryTickersPriceInfo(String[] ticker, int count, int exchangeId);

    /**
     *  订阅全市场的期权行情
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int subscribeAllOptionMarketData(int exchangeId);

    /**
     * 退订全市场的期权行情
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int unSubscribeAllOptionMarketData(int exchangeId);

    /**
     * 订阅全市场的期权行情订单簿
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int subscribeAllOptionOrderBook(int exchangeId);

    /**
     * 退订全市场的期权行情订单簿
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int unSubscribeAllOptionOrderBook(int exchangeId);

    /**
     * 订阅全市场的期权逐笔行情
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int subscribeAllOptionTickByTick(int exchangeId);

    /**
     * 退订全市场的期权逐笔行情
     * @param exchangeId 交易所代码
     * @return 订阅接口调用是否成功，“0”表示接口调用成功，非“0”表示接口调用出错
     */
    public native int unSubscribeAllOptionTickByTick(int exchangeId);


    //======================callback functions=====================================

    /**
     *  当客户端与行情后台通信连接断开时，该方法被调用。
     * api不会自动重连，当断线发生时，请用户自行选择后续操作。可以在此函数中调用Login重新登录。注意用户重新登录后，需要重新订阅行情。
     * @param reason 错误原因
     */
    private void onDisconnected(int reason) {
        quoteSpi.onDisconnected(reason);
    }

    /**
     *  此函数只有在服务器发生错误时才会调用，一般无需用户处理
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onError(ErrorMessage errorMessage) {
        quoteSpi.onError(errorMessage);
    }

    /**
     *  订阅行情应答，包括股票、指数和期权。每条订阅的合约均对应一条订阅应答，需要快速返回，否则会堵塞后续消息，当堵塞严重时，会触发断线
     * @param rsp 详细的合约订阅情况
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onSubMarketData(SpecificTickerResponse rsp, ErrorMessage errorMessage) {
        quoteSpi.onSubMarketData(rsp, errorMessage);
    }

    /**
     *  退订行情应答，包括股票、指数和期权。每条取消订阅的合约均对应一条取消订阅应答，需要快速返回，否则会堵塞后续消息，当堵塞严重时，会触发断线
     * @param rsp 详细的合约订阅情况
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onUnSubMarketData(SpecificTickerResponse rsp, ErrorMessage errorMessage) {
        quoteSpi.onUnSubMarketData(rsp, errorMessage);
    }

    /**
     *  深度行情通知，包含买一卖一队列
     * @param rsp 行情数据
     * @param ext 行情扩展数据
     */
    private void onDepthMarketData(DepthMarketDataResponse rsp, DepthMarketDataExResponse ext) {
        quoteSpi.onDepthMarketData(rsp, ext);
    }

    /**
     *  订阅行情订单簿应答，包括股票、指数和期权。每条订阅的合约均对应一条订阅应答，需要快速返回，否则会堵塞后续消息，当堵塞严重时，会触发断线
     * @param rsp 详细的合约订阅情况
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onSubOrderBook(SpecificTickerResponse rsp, ErrorMessage errorMessage) {
        quoteSpi.onSubOrderBook(rsp, errorMessage);
    }

    /**
     *  退订行情订单簿应答，包括股票、指数和期权。每条取消订阅的合约均对应一条取消订阅应答，需要快速返回，否则会堵塞后续消息，当堵塞严重时，会触发断线
     * @param rsp 详细的合约订阅情况
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onUnSubOrderBook(SpecificTickerResponse rsp, ErrorMessage errorMessage) {
        quoteSpi.onUnSubOrderBook(rsp, errorMessage);
    }

    /**
     *  行情订单簿通知，包括股票、指数和期权。行情订单簿数据，需要快速返回，否则会堵塞后续消息，当堵塞严重时，会触发断线
     * @param rsp 行情订单簿数据，需要快速返回，否则会堵塞后续消息，当堵塞严重时，会触发断线
     */
    private void onOrderBook(OrderBookResponse rsp) {
        quoteSpi.onOrderBook(rsp);
    }

    /**
     *  逐笔行情通知，包括股票、指数和期权
     * @param rsp 逐笔行情数据，包括逐笔委托和逐笔成交，此为共用结构体，需要根据type来区分是逐笔委托还是逐笔成交，需要快速返回，否则会堵塞后续消息，当堵塞严重时，会触发断线
     */
    private void onTickByTick(TickByTickResponse rsp) {
        quoteSpi.onTickByTick(rsp);
    }

    /**
     *  订阅全市场的股票行情应答,需要快速返回
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onSubscribeAllMarketData(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onSubscribeAllMarketData(exchangeId, errorMessage);
    }

    /**
     *  退订全市场的股票行情应答，需要快速返回
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onUnSubscribeAllMarketData(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onUnSubscribeAllMarketData(exchangeId, errorMessage);
    }

    /**
     *  订阅全市场的股票行情订单簿应答，需要快速返回
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onSubscribeAllOrderBook(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onSubscribeAllOrderBook(exchangeId, errorMessage);
    }

    /**
     *  退订全市场的股票行情订单簿应答，需要快速返回
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onUnSubscribeAllOrderBook(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onUnSubscribeAllOrderBook(exchangeId, errorMessage);
    }

    /**
     *  订阅全市场的股票逐笔行情应答，需要快速返回
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onSubscribeAllTickByTick(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onSubscribeAllTickByTick(exchangeId, errorMessage);
    }

    /**
     *  退订全市场的股票逐笔行情应答，需要快速返回
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onUnSubscribeAllTickByTick(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onUnSubscribeAllTickByTick(exchangeId, errorMessage);
    }

    /**
     *  查询可交易合约的应答
     * @param rsp 可交易合约信息
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onQueryAllTickers(TickerInfoResponse rsp, ErrorMessage errorMessage) {
        quoteSpi.onQueryAllTickers(rsp, errorMessage);
    }

    /**
     *  订阅逐笔行情应答，包括股票、指数和期权。每条订阅的合约均对应一条订阅应答，需要快速返回，否则会堵塞后续消息，当堵塞严重时，会触发断线
     * @param ticker 详细的合约订阅情况
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onSubTickByTick (SpecificTickerResponse ticker, ErrorMessage errorMessage){
        quoteSpi.onSubTickByTick(ticker, errorMessage);
    }

    /**
     *  退订逐笔行情应答，包括股票、指数和期权
     * @param ticker 详细的合约取消订阅情况
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onUnSubTickByTick(SpecificTickerResponse ticker, ErrorMessage errorMessage) {
        quoteSpi.onUnSubTickByTick(ticker, errorMessage);
    }

    /**
     *  查询合约的最新价格信息应答
     * @param tickerInfo 合约的最新价格信息
     * @param errorMessage 当服务器响应发生错误时的具体的错误代码和错误信息，当errorMessage为空，或者errorMessage.error_id为0时，表明没有错误
     */
    private void onQueryTickersPriceInfo(TickerPriceInfoResponse tickerInfo, ErrorMessage errorMessage) {
        quoteSpi.onQueryTickersPriceInfo(tickerInfo, errorMessage);
    }

    /**
     * 订阅全市场的期权行情应答
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 取消订阅合约时发生错误时返回的错误信息，当error_info为空，或者error_info.error_id为0时，表明没有错误
     */
    private void onSubscribeAllOptionMarketData(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onSubscribeAllOptionMarketData(exchangeId,errorMessage);
    }

    /**
     * 退订全市场的期权行情应答
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 取消订阅合约时发生错误时返回的错误信息，当error_info为空，或者error_info.error_id为0时，表明没有错误
     */
    private void onUnSubscribeAllOptionMarketData(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onUnSubscribeAllOptionMarketData(exchangeId,errorMessage);
    }

    /**
     * 订阅全市场的期权行情订单簿应答
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 取消订阅合约时发生错误时返回的错误信息，当error_info为空，或者error_info.error_id为0时，表明没有错误
     */
    private void onSubscribeAllOptionOrderBook(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onSubscribeAllOptionOrderBook(exchangeId,errorMessage);
    }

    /**
     * 退订全市场的期权行情订单簿应答
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 取消订阅合约时发生错误时返回的错误信息，当error_info为空，或者error_info.error_id为0时，表明没有错误
     */
    private void onUnSubscribeAllOptionOrderBook(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onUnSubscribeAllOptionOrderBook(exchangeId,errorMessage);
    }

    /**
     * 订阅全市场的期权逐笔行情应答
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 取消订阅合约时发生错误时返回的错误信息，当error_info为空，或者error_info.error_id为0时，表明没有错误
     */
    private void onSubscribeAllOptionTickByTick(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onSubscribeAllOptionTickByTick(exchangeId,errorMessage);
    }


    /**
     * 退订全市场的期权逐笔行情应答
     * @param exchangeId 表示当前全订阅的市场，如果为XTP_EXCHANGE_UNKNOWN，表示沪深全市场，XTP_EXCHANGE_SH表示为上海全市场，XTP_EXCHANGE_SZ表示为深圳全市场
     * @param errorMessage 取消订阅合约时发生错误时返回的错误信息，当error_info为空，或者error_info.error_id为0时，表明没有错误
     */
    private void onUnSubscribeAllOptionTickByTick(int exchangeId, ErrorMessage errorMessage) {
        quoteSpi.onUnSubscribeAllOptionTickByTick(exchangeId,errorMessage);
    }
}
