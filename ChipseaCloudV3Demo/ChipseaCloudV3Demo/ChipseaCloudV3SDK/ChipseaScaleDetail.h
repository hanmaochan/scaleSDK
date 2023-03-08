//
//  ChipseaScaleDetail.h
//  ChipseaCloudV3SDK
//
//  Created by iChipsea on 2023/1/13.
//

#import <Foundation/Foundation.h>


@interface ChipseaScaleDetail : NSObject

/**
 *  获得单例 |  get singleton  |  シングルトンを取得
 *
 *  @return 返回单例  |  return singleton   |   シングルトンを返す
 */
+(ChipseaScaleDetail *)getInstance;



/**
 * 设置用户信息   |   Set user information    |    ユーザー情報の設定
 * @param height 身高 |   height  |   身長
 * @param weight 体重 |   weight  |   重さ
 * @param sex  男-1 女-0  |       Male-1 Female-0     |       男性-1 女性-0
 * @param age 年龄    |   age     |       年
 * @param resistance 主电阻  r1    |   resistance      |    抵抗
 */
-(void)setUserInfo_height:(float)height weight:(float)weight sex:(Byte)sex age:(int)age resistance:(float)resistance;


/**
 * 获取细胞外液   |   get extracellular fluid     |       細胞外液を得る
 */
-(float)getEXF;

/**
 * 获取细胞内液   |   get intracellular fluid     |       細胞内液を得る
 */
-(float)getInF;

/**
 * 获取总水重    |   Get total water weight      |       水の総重量を取得する
 */
-(float)getTF;

/**
 * 获取含水百分比  |   Get moisture percentage     |       水分率を取得する
 */
-(float)getTFR;

/**
 * 获取去脂体重(瘦体重)      |       Obtaining Lean Body Mass (Lean Mass)        |       除脂肪体重（除脂肪体重）を取得する
 */
-(float)getLBM;

/**
 * 获取肌肉重(含水)    |   Gain muscle weight (with water)     |   筋肉量を増やす（水で）
 */
-(float)getSLM;

/**
 * 获取肌肉率    |   gain muscle rate    |   筋肉率を得る
 *
 */
-(float)getSLMPrecent;

/**
 * 获取骨骼肌(kg)    |   Get skeletal muscle (kg)    |   骨格筋を得る（kg）
 **/
-(float)getSMM;

/**
 * 获取蛋白质    |   get protein     |   タンパク質を得る
 */
-(float)getPM;

/**
 * 获取脂肪重    |   get fat weight  |   太る
 */
-(float)getFM;

/**
 * 获取脂肪百份比  |   Get Percent Fat     |   脂肪率を取得
 */
-(float)getBFR;

/**
 * 获取水肿测试   |   Get an Edema Test   |   浮腫テストを受ける
 */
-(float)getEE;

/**
 * 获取肥胖度    |   get obesity     |       肥満になる
 */
-(float)getOD;

/**
 * 获取肌肉控制   |   gain muscle control     |       筋肉のコントロールを得る
 */
-(float)getMC;

/**
 * 获取体重控制   |   Get Weight Control  |   体重管理を取得
 */
-(float)getWC;

/**
 * 获取脂肪控制   |   Get Fat Control     |       脂肪をコントロールする
 */
-(float)getFC;

/**
 * 获取基础代谢   |   get basal metabolism    |   基礎代謝を得る
 */
-(float)getBMR;

/**
 * 获取骨(无机盐) |   Obtain bone (inorganic salt)    |   骨（無機塩）を入手する
 */
-(float)getMSW;

/**
 * 获取内脏脂肪等级 |   Get Visceral Fat Levels |   内臓脂肪レベルを取得する
 */
-(float)getVFR;

/**
 * 身体年龄   |   body age    |   身体年齢
 */
-(float)getBodyAge;

/**
 * 获取标准体重   |   get standard weight |   標準体重になる
 */
-(float)getBW;

/**
 * 获取个体成分评分 |   Get Individual Component Scores |    個々のコンポーネント スコアを取得する
 *
 */
-(float)getTotalScore;


-(void)print;

@end
