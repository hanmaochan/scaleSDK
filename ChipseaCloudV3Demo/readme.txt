1������SDK����ļ���

2������Ҫ��������ChipseaCloudV3SDK.h
#import "ChipseaCloudV3SDK.h"

3����ʼ����������㷨��
ChipseaBtUtil * btUtil  =[ChipseaBtUtil getInstance];
btUtil.delegate = self;
   btUtil.stopAdvertisementState = NO;
    
ChipseaScaleDetail * scaleDetail = [ChipseaScaleDetail getInstance];

4��ʵ����ش��������������ھ������ݿɸ���ʵ���߼���Ҫ�޸�
��ҪҪʵ�ֵĴ������У�
4.1 �㲥���ݽ�����Ļص�
-(void)currentBroadcastPeripheral:(CBPeripheral *)peripheral btData:(BtData *)device;
�ڱ������п�ʵ���ж��������Ĺ㲥�ź��Ƿ�������Ҫ���ӵģ��������������ӷ�����


4.2�Ѿ����ӣ����ӳɹ��ص�
-(void)connectedPeripheral:(CBPeripheral *)peripheral;


4.3͸�����ݽ�����Ļص�
-(void)afterConnectBtData:(BtData *)device;
�ڱ�������ʵ���յ�͸�����ݺ���߼�����
���ݹ������ݡ�ʵʱ���ݣ��ֱ�ʵ������߼���

4.4�������ӶϿ��ص�
-(void)didDisconnectPeripheral:(CBPeripheral *)peripheral error:(NSError *)error;
�������Ͽ����ӣ���ص�������


4��5��������״̬�仯ʱ��Ļص�
-(void)didUpdateCsScaleState:(CsScaleState)state;
�ڱ������з��ص�����״̬�ı仯

4.6���ش���ص�
-(void)chipseaBtUtilErrorType:(ChipseaErrorType)errorType;
�ڱ������з��ش��󣬰���BundleID ��ƥ�䣬Mac��ַ��ƥ�䡣


5���ڰ����������豸ʱ��������������
[ChipseaBtUtil setCurBtDeviceType: BtDeviceType_Weight macAddress:nil isBounding:YES];

���������������豸ʱ��������������
[ChipseaBtUtil setCurBtDeviceType: BtDeviceType_Weight macAddress:@����ָ����Mac��ַ�� isBounding:NO];


6���㷨������أ����( cm)  �� ����(kg)  �� �Ա�(��-1 Ů-0) �� ���� �� ����
	[ChipseaScaleDetail setUserInfo_height:��� weight:���� sex:�Ա� age:���� resistance:����];
	�����뿴Demo.