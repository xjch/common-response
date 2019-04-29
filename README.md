# common-response
## 说明
- 对通用的响应结果进行定义

*  响应字段说明

| 名称  | 类型  | 说明 |Description|
|---|---|---|---|
| reqResult | boolean | 请求成功与否。true:请求成功；false请求失败 |request result|
| respErrorCode | string | 请求返回的错误编码。 00000000表示success，非00000000表示失败错误 |error code. 00000000 represent success, >00000000 represent failure code |
| respErrorMsg | string | 请求失败返回的错误信息 |error message from failed request|
| respResult | object| 请求返回的数据 |return data|

