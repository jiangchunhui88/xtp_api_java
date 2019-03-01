/////////////////////////////////////////////////////////////////////////
///@author ��̩֤ȯ�ɷ����޹�˾
///@file xoms_api_struct.h
///@brief ���彻����������ݽṹ
/////////////////////////////////////////////////////////////////////////
#ifndef _XOMS_API_STRUCT_H_
#define _XOMS_API_STRUCT_H_

#include "xtp_api_data_type.h"
#include "stddef.h"

#pragma pack(8)

//=====================�ͻ��˽ӿڶ���=================================
///�¶�������
struct XTPOrderInsertInfo
{
    ///XTPϵͳ����ID�������û���д����XTPϵͳ��Ψһ
    uint64_t                order_xtp_id;
    ///�������ã��ɿͻ��Զ���
    uint32_t	            order_client_id;
    ///��Լ���� �ͻ������󲻴��ո���'\0'��β
    char                    ticker[XTP_TICKER_LEN];
    ///�����г�
    XTP_MARKET_TYPE         market;
    ///�۸�
    double                  price;
    ///ֹ��ۣ������ֶΣ�
    double                  stop_price;
    ///����(��Ʊ��λΪ�ɣ���ع���λΪ��)
    int64_t                 quantity;
    ///�����۸�
    XTP_PRICE_TYPE          price_type;
    union{
        uint32_t            u32;
        struct {
            ///��������
            XTP_SIDE_TYPE               side;
            ///��ƽ��־
            XTP_POSITION_EFFECT_TYPE    position_effect;
			///Ԥ���ֶ�1
            uint8_t                     reserved1;
			///Ԥ���ֶ�2
			uint8_t                     reserved2;
        };
    };
	///ҵ������
	XTP_BUSINESS_TYPE       business_type;
 };


///����ʧ����Ӧ��Ϣ
struct XTPOrderCancelInfo
{
    ///����XTPID
    uint64_t                 order_cancel_xtp_id;
    ///ԭʼ����XTPID
    uint64_t                 order_xtp_id;
};


///������Ӧ�ṹ��
struct XTPOrderInfo
{
    ///XTPϵͳ����ID����XTPϵͳ��Ψһ
	uint64_t                order_xtp_id;
	///�������ã��û��Զ���
	uint32_t	            order_client_id;
    ///�����������ã��û��Զ��壨��δʹ�ã�
    uint32_t                order_cancel_client_id;
    ///������XTPϵͳ�е�id����XTPϵͳ��Ψһ
    uint64_t                order_cancel_xtp_id;
	///��Լ����
	char                    ticker[XTP_TICKER_LEN];
	///�����г�
	XTP_MARKET_TYPE         market;
	///�۸�
	double                  price;
	///�������˶����ı�������
	int64_t                 quantity;
	///�����۸�����
	XTP_PRICE_TYPE          price_type;
    union{
        uint32_t            u32;
        struct {
            ///��������
            XTP_SIDE_TYPE               side;
            ///��ƽ��־
            XTP_POSITION_EFFECT_TYPE    position_effect;
			///Ԥ���ֶ�1
			uint8_t                     reserved1;
			///Ԥ���ֶ�2
			uint8_t                     reserved2;
        };
    };
	///ҵ������
	XTP_BUSINESS_TYPE       business_type;
	///��ɽ�������Ϊ�˶����ۼƳɽ�����
	int64_t                 qty_traded;
	///ʣ���������������ɹ�ʱ����ʾ��������
	int64_t                 qty_left;
	///ί��ʱ�䣬��ʽΪYYYYMMDDHHMMSSsss
	int64_t                 insert_time;
	///����޸�ʱ�䣬��ʽΪYYYYMMDDHHMMSSsss
	int64_t                 update_time;
	///����ʱ�䣬��ʽΪYYYYMMDDHHMMSSsss
	int64_t                 cancel_time;
	///�ɽ���Ϊ�˶����ĳɽ��ܽ��
	double                  trade_amount;
	///���ر������ OMS���ɵĵ��ţ�����ͬ��order_xtp_id��Ϊ�������������̵ĵ���
	char                    order_local_id[XTP_LOCAL_ORDER_LEN];
	///����״̬��������Ӧ��û�в��ֳɽ�״̬�����ͣ��ڲ�ѯ��������У����в��ֳɽ�״̬
	XTP_ORDER_STATUS_TYPE   order_status;
	///�����ύ״̬��OMS�ڲ�ʹ�ã��û��������
	XTP_ORDER_SUBMIT_STATUS_TYPE   order_submit_status;
	///��������
	TXTPOrderTypeType       order_type;
};



///�����ɽ��ṹ��
struct XTPTradeReport
{
    ///XTPϵͳ����ID���˳ɽ��ر���صĶ���ID����XTPϵͳ��Ψһ
    uint64_t                 order_xtp_id;
    ///��������
    uint32_t                 order_client_id;
    ///��Լ����
    char                     ticker[XTP_TICKER_LEN];
    ///�����г�
    XTP_MARKET_TYPE          market;
    ///�����ţ�����XTPID�󣬸��ֶ�ʵ�ʺ�order_xtp_id�ظ����ӿ�����ʱ������
    uint64_t                 local_order_id;
    ///�ɽ���ţ����Ψһ���Ͻ���ÿ�ʽ���Ψһ��������2�ʳɽ��ر�ӵ����ͬ��exec_id���������Ϊ�˱ʽ����Գɽ�
    char                     exec_id[XTP_EXEC_ID_LEN];
    ///�۸񣬴˴γɽ��ļ۸�
    double                   price;
    ///�������˴γɽ��������������ۼ�����
    int64_t                  quantity;
    ///�ɽ�ʱ�䣬��ʽΪYYYYMMDDHHMMSSsss
    int64_t                  trade_time;
    ///�ɽ����˴γɽ����ܽ�� = price*quantity
    double                   trade_amount;
    ///�ɽ���� --�ر���¼�ţ�ÿ��������Ψһ,report_index+market�ֶο������Ψһ��ʶ��ʾ�ɽ��ر�
    uint64_t                 report_index;
    ///������� --���������ţ��Ͻ���Ϊ�գ�����д��ֶ�
    char                     order_exch_id[XTP_ORDER_EXCH_LEN];
    ///�ɽ�����  --�ɽ��ر��е�ִ������
    TXTPTradeTypeType        trade_type;
    union{
        uint32_t            u32;
        struct {
            ///��������
            XTP_SIDE_TYPE               side;
            ///��ƽ��־
            XTP_POSITION_EFFECT_TYPE    position_effect;
			///Ԥ���ֶ�1
			uint8_t                     reserved1;
			///Ԥ���ֶ�2
			uint8_t                     reserved2;
        };
    };
	///ҵ������
	XTP_BUSINESS_TYPE        business_type;
    ///����������Ա���� 
    char                     branch_pbu[XTP_BRANCH_PBU_LEN];
};


//////////////////////////////////////////////////////////////////////////
///������ѯ
//////////////////////////////////////////////////////////////////////////
///������ѯ����-������ѯ
struct XTPQueryOrderReq
{
    ///֤ȯ���룬����Ϊ�գ����Ϊ�գ���Ĭ�ϲ�ѯʱ����ڵ����гɽ��ر�
    char      ticker[XTP_TICKER_LEN];
    ///��ʽΪYYYYMMDDHHMMSSsss��Ϊ0��Ĭ�ϵ�ǰ������0��
    int64_t   begin_time;
    ///��ʽΪYYYYMMDDHHMMSSsss��Ϊ0��Ĭ�ϵ�ǰʱ��
    int64_t   end_time;  
};

///������ѯ��Ӧ�ṹ��
typedef struct XTPOrderInfo XTPQueryOrderRsp;


//////////////////////////////////////////////////////////////////////////
///�ɽ��ر���ѯ
//////////////////////////////////////////////////////////////////////////
///��ѯ�ɽ���������-����ִ�б�Ų�ѯ�������ֶΣ�
struct XTPQueryReportByExecIdReq
{
    ///XTP����ϵͳID
    uint64_t  order_xtp_id;  
    ///�ɽ�ִ�б��
    char  exec_id[XTP_EXEC_ID_LEN];
};

///��ѯ�ɽ��ر�����-��ѯ����
struct XTPQueryTraderReq
{
    ///֤ȯ���룬����Ϊ�գ����Ϊ�գ���Ĭ�ϲ�ѯʱ����ڵ����гɽ��ر�
    char      ticker[XTP_TICKER_LEN];
    ///��ʼʱ�䣬��ʽΪYYYYMMDDHHMMSSsss��Ϊ0��Ĭ�ϵ�ǰ������0��
    int64_t   begin_time; 
    ///����ʱ�䣬��ʽΪYYYYMMDDHHMMSSsss��Ϊ0��Ĭ�ϵ�ǰʱ��
    int64_t   end_time;  
};

///�ɽ��ر���ѯ��Ӧ�ṹ��
typedef struct XTPTradeReport  XTPQueryTradeRsp;



//////////////////////////////////////////////////////////////////////////
///�˻��ʽ��ѯ��Ӧ�ṹ��
//////////////////////////////////////////////////////////////////////////
struct XTPQueryAssetRsp
{
    ///���ʲ�(=�����ʽ� + ֤ȯ�ʲ���ĿǰΪ0��+ Ԥ�۵��ʽ�)
    double total_asset;
    ///�����ʽ�
    double buying_power;
    ///֤ȯ�ʲ��������ֶΣ�ĿǰΪ0��
    double security_asset;
    ///�ۼ�����ɽ�֤ȯռ���ʽ�
    double fund_buy_amount;
    ///�ۼ�����ɽ����׷���
    double fund_buy_fee;
    ///�ۼ������ɽ�֤ȯ�����ʽ�
    double fund_sell_amount;
    ///�ۼ������ɽ����׷���
    double fund_sell_fee;
    ///XTPϵͳԤ�۵��ʽ𣨰�����������ƱʱԤ�۵Ľ����ʽ�+Ԥ�������ѣ�
    double withholding_amount;
    ///�˻�����
    XTP_ACCOUNT_TYPE account_type;

    ///����ı�֤��
    double frozen_margin;
    ///��Ȩ�����ʽ�
    double frozen_exec_cash;
    ///��Ȩ����
    double frozen_exec_fee;
    ///�渶�ʽ�
    double pay_later;
    ///Ԥ�渶�ʽ�
    double preadva_pay;
    ///�������
    double orig_banlance;
    ///��ǰ���
    double banlance;
    ///��������
    double deposit_withdraw;
    ///���ս����ʽ�����
    double trade_netting;
    ///�ʽ��ʲ�
    double captial_asset;

    ///ǿ���ʽ�
    double force_freeze_amount;
    ///��ȡ�ʽ�
    double preferred_amount;

    ///(�����ֶ�)
    uint64_t unknown[43 - 12];
};



//////////////////////////////////////////////////////////////////////////
///��ѯ��Ʊ�ֲ����
//////////////////////////////////////////////////////////////////////////
struct XTPQueryStkPositionRsp
{
    ///֤ȯ����
    char                ticker[XTP_TICKER_LEN];
    ///֤ȯ����
    char                ticker_name[XTP_TICKER_NAME_LEN];
    ///�����г�
    XTP_MARKET_TYPE     market;
    ///�ֲܳ�
    int64_t             total_qty;
    ///�����ֲ�
    int64_t				sellable_qty;
    ///�ֲֳɱ�
    double              avg_price;
    ///����ӯ���������ֶΣ�
    double              unrealized_pnl;
    ///���ճֲ�
    int64_t             yesterday_position;
    ///�����깺����������깺���������������ͬʱ���ڣ���˿��Թ���һ���ֶΣ�
    int64_t				purchase_redeemable_qty;

    /// �ֲַ���
	XTP_POSITION_DIRECTION_TYPE      position_direction;
	///�����ֶ�1
	uint32_t			reserved1;
    /// ����Ȩ��Լ
    int64_t             executable_option;
    /// ���������
    int64_t             lockable_position;
    /// ����Ȩ���
    int64_t             executable_underlying;
    /// ���������
    int64_t             locked_position;
    /// �������������
    int64_t             usable_locked_position;


    ///(�����ֶ�)
    uint64_t unknown[50 - 6];
};


/////////////////////////////////////////////////////////////////////////
///�ʽ���ת��ˮ֪ͨ
/////////////////////////////////////////////////////////////////////////
struct XTPFundTransferNotice
{
    ///�ʽ���ת���
    uint64_t	            serial_id;
    ///��ת����
    XTP_FUND_TRANSFER_TYPE	transfer_type;
    ///���
    double	                amount;
    ///������� 
    XTP_FUND_OPER_STATUS    oper_status;
    ///����ʱ��
    uint64_t	            transfer_time;
};



/////////////////////////////////////////////////////////////////////////
///�ʽ���ת��ˮ��ѯ��������Ӧ
/////////////////////////////////////////////////////////////////////////
struct XTPQueryFundTransferLogReq {
    ///�ʽ���ת���
    uint64_t	serial_id;

};

/////////////////////////////////////////////////////////////////////////
///�ʽ���ת��ˮ��¼�ṹ��
/////////////////////////////////////////////////////////////////////////
typedef struct XTPFundTransferNotice XTPFundTransferLog;

//////////////////////////////////////////////////////////////////////////
///��ѯ�ּ�������Ϣ�ṹ��
//////////////////////////////////////////////////////////////////////////
struct XTPQueryStructuredFundInfoReq
{
	XTP_EXCHANGE_TYPE   exchange_id;  ///<���������룬����Ϊ��
	char                sf_ticker[XTP_TICKER_LEN];   ///<�ּ�����ĸ������룬����Ϊ�գ����Ϊ�գ���Ĭ�ϲ�ѯ���еķּ�����
};

//////////////////////////////////////////////////////////////////////////
///��ѯ�ּ�������Ϣ��Ӧ�ṹ��
//////////////////////////////////////////////////////////////////////////
struct XTPStructuredFundInfo
{
    XTP_EXCHANGE_TYPE   exchange_id;  ///<����������
	char                sf_ticker[XTP_TICKER_LEN];   ///<�ּ�����ĸ�������
	char                sf_ticker_name[XTP_TICKER_NAME_LEN]; ///<�ּ�����ĸ��������
    char                ticker[XTP_TICKER_LEN];   ///<�ּ������ӻ������
    char                ticker_name[XTP_TICKER_NAME_LEN]; ///<�ּ������ӻ�������
	XTP_SPLIT_MERGE_STATUS	split_merge_status;   ///<����������ֺϲ�״̬
    uint32_t            ratio; ///<��ֺϲ�����
    uint32_t            min_split_qty;///<��С�������
    uint32_t            min_merge_qty; ///<��С�ϲ�����
    double              net_price;///<����ֵ
};


//////////////////////////////////////////////////////////////////////////
///��ѯ��ƱETF��Լ�������--����ṹ��,
///�������Ϊ����������:1,�����򷵻������г���ETF��Լ��Ϣ��
///                  2,ֻ��дmarket,���ظý����г��½��
///                   3,��дmarket��ticker����,ֻ���ظ�etf��Ϣ��
//////////////////////////////////////////////////////////////////////////
struct XTPQueryETFBaseReq
{
    ///�����г�
    XTP_MARKET_TYPE    market;
    ///ETF��������
    char               ticker[XTP_TICKER_LEN];
};

//////////////////////////////////////////////////////////////////////////
///��ѯ��ƱETF��Լ�������--��Ӧ�ṹ��
//////////////////////////////////////////////////////////////////////////
typedef struct XTPQueryETFBaseRsp
{
    XTP_MARKET_TYPE     market;                             ///<�����г�
    char                etf[XTP_TICKER_LEN];                ///<etf����,����,����ͳһʹ�øô���
    char                subscribe_redemption_ticker[XTP_TICKER_LEN];    ///<etf�깺��ش���
    int32_t             unit;                               ///<��С�깺��ص�λ��Ӧ��ETF����,������֤"50ETF"����900000
    int32_t             subscribe_status;                   ///<�Ƿ������깺,1-����,0-��ֹ
    int32_t             redemption_status;                  ///<�Ƿ��������,1-����,0-��ֹ
    double              max_cash_ratio;                     ///<����ֽ��������,С��1����ֵ   TODO �Ƿ����double
    double              estimate_amount;                    ///<T��Ԥ�����
    double              cash_component;                     ///<T-X���ֽ���
    double              net_value;                          ///<����λ��ֵ
    double              total_amount;                       ///<��С���굥λ��ֵ�ܽ��=net_value*unit
}XTPQueryETFBaseRsp;



//////////////////////////////////////////////////////////////////////////
///��ѯ��ƱETF��Լ�ɷֹ���Ϣ--����ṹ��,�������Ϊ:�����г�+ETF��������
//////////////////////////////////////////////////////////////////////////
typedef struct XTPQueryETFComponentReq
{
    ///�����г�
    XTP_MARKET_TYPE     market;
    ///ETF��������
    char                ticker[XTP_TICKER_LEN];
}XTPQueryETFComponentReq;


//////////////////////////////////////////////////////////////////////////
///��ѯ��ƱETF��Լ�ɷֹ���Ϣ--��Ӧ�ṹ��
//////////////////////////////////////////////////////////////////////////
struct XTPQueryETFComponentRsp
{
    ///�����г�
    XTP_MARKET_TYPE     market;
    ///ETF����
    char                ticker[XTP_TICKER_LEN];
    ///�ɷݹɴ���
    char                component_ticker[XTP_TICKER_LEN];
    ///�ɷݹ�����
    char                component_name[XTP_TICKER_NAME_LEN];
    ///�ɷݹ�����
    int64_t             quantity;
    ///�ɷݹɽ����г�
    XTP_MARKET_TYPE     component_market;
    ///�ɷݹ������ʶ
    ETF_REPLACE_TYPE    replace_type;
    ///��۱���
    double              premium_ratio;
    ///�ɷֹ������ʶΪ�����ֽ����ʱ����ܽ��
    double              amount;

};

//////////////////////////////////////////////////////////////////////////
///��ѯ���տ��깺�¹���Ϣ
//////////////////////////////////////////////////////////////////////////
struct XTPQueryIPOTickerRsp {
    ///�����г�
    XTP_MARKET_TYPE     market;
    ///�깺����
    char                ticker[XTP_TICKER_LEN];
    ///�깺��Ʊ����
    char                ticker_name[XTP_TICKER_NAME_LEN]; 
    ///�깺�۸�
    double              price;
    ///�깺��Ԫ         
    int32_t             unit;
    ///��������깺����
    int32_t             qty_upper_limit;
};


//////////////////////////////////////////////////////////////////////////
///��ѯ�û��깺���
//////////////////////////////////////////////////////////////////////////
struct XTPQueryIPOQuotaRsp {
    ///�����г�
    XTP_MARKET_TYPE     market;
    ///���깺���
    int32_t             quantity;
};

//////////////////////////////////////////////////////////////////////////
///��ѯ��Ȩ���۽���ҵ��ο���Ϣ--����ṹ��,�������Ϊ:�����г�+8λ��Ȩ����
//////////////////////////////////////////////////////////////////////////
struct XTPQueryOptionAuctionInfoReq {
    ///�����г�
    XTP_MARKET_TYPE     market;
    ///8λ��Ȩ��Լ����
    char                ticker[XTP_TICKER_LEN];
};

//////////////////////////////////////////////////////////////////////////
///��ѯ��Ȩ���۽���ҵ��ο���Ϣ
//////////////////////////////////////////////////////////////////////////
struct XTPQueryOptionAuctionInfoRsp {
    char                ticker[XTP_TICKER_LEN];             ///<��Լ���룬����ticker���ñ��ֶ�
    XTP_MARKET_TYPE     security_id_source;                 ///<֤ȯ����Դ
    char                symbol[XTP_TICKER_NAME_LEN];        ///<��Լ���
    char                contract_id[XTP_TICKER_NAME_LEN];   ///<��Լ���״���
    char                underlying_security_id[XTP_TICKER_LEN]; ///<����֤ȯ����
	XTP_MARKET_TYPE     underlying_security_id_source;      ///<����֤ȯ����Դ

    uint32_t            list_date;                          ///<�������ڣ���ʽΪYYYYMMDD
    uint32_t            last_trade_date;                    ///<������գ���ʽΪYYYYMMDD
    XTP_TICKER_TYPE     ticker_type;                        ///<֤ȯ���
    int32_t             day_trading;                        ///<�Ƿ�֧�ֵ��ջ�ת���ף�1-������0-������

    XTP_OPT_CALL_OR_PUT_TYPE    call_or_put;                ///<�Ϲ����Ϲ�
    uint32_t            delivery_day;                       ///<��Ȩ�����գ���ʽΪYYYYMMDD
    uint32_t            delivery_month;                     ///<�����·ݣ���ʽΪYYYYMM

    XTP_OPT_EXERCISE_TYPE_TYPE  exercise_type;              ///<��Ȩ��ʽ
    uint32_t            exercise_begin_date;                ///<��Ȩ��ʼ���ڣ���ʽΪYYYYMMDD
    uint32_t            exercise_end_date;                  ///<��Ȩ�������ڣ���ʽΪYYYYMMDD
    double              exercise_price;                     ///<��Ȩ�۸�

    int64_t             qty_unit;                           ///<������λ������ĳһ֤ȯ�걨��ί�У���ί�������ֶα���Ϊ��֤ȯ������λ��������
    int64_t             contract_unit;                      ///<��Լ��λ
    int64_t             contract_position;                  ///<��Լ�ֲ���

    double              prev_close_price;                   ///<��Լǰ���̼�
    double              prev_clearing_price;                ///<��Լǰ�����

    int64_t             lmt_buy_max_qty;                    ///<�޼��������
    int64_t             lmt_buy_min_qty;                    ///<�޼�����С��
    int64_t             lmt_sell_max_qty;                   ///<�޼��������
    int64_t             lmt_sell_min_qty;                   ///<�޼�����С��
    int64_t             mkt_buy_max_qty;                    ///<�м��������
    int64_t             mkt_buy_min_qty;                    ///<�м�����С��
    int64_t             mkt_sell_max_qty;                   ///<�м��������
    int64_t             mkt_sell_min_qty;                   ///<�м�����С��

    double              price_tick;                         ///<��С���۵�λ
    double              upper_limit_price;                  ///<��ͣ��
    double              lower_limit_price;                  ///<��ͣ��
    double              sell_margin;                        ///<������ÿ�ű�֤��
    double              margin_ratio_param1;                ///<��������֤������������һ
    double              margin_ratio_param2;                ///<��������֤��������������

    uint64_t            unknown[20];                        ///<�������ֶΣ�
};


#pragma pack()
#endif //_XOMS_API_STRUCT_H_
