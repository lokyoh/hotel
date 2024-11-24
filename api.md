# 酒店管理系统接口文档

## 用户接口

### 1.1 用户注册x

##### 接口功能:

> 用于注册新用户

##### 请求路径:

> /api/user/register

##### 请求方式:

> POST

##### 请求参数:

| 参数       | 类型     | 必须   | 说明       |
|:---------|:-------|:-----|:---------|
| cname    | string | true | 用户名      |
| phone    | string | true | 手机号      |
| password | string | true | 密码，6-16位 |

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    | null   | false | 返回的数据       |

##### 接口示例:

> http://server.lokyoh.com:10080/api/user/register?&

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": null
}
```

### 1.2 用户登录x

##### 接口功能:

> 用于用户登录验证

##### 请求路径:

> /api/user/login

##### 请求方式:

> POST

##### 请求参数:

| 参数 | 类型 | 必须 | 默认 | 说明 |
|:---|:---|:---|:---|:---|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    | string | false | 用户令牌        |

##### 接口示例:

> http://server.lokyoh.com:10080/api/user/login?&

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": {
        "": ""
    }
}
```

## 2 员工接口

### 2.1 员工登录

##### 接口功能:

> 用于员工登录

##### 请求路径:

> /api/worker/login

##### 请求方式:

> POST

##### 请求参数:

| 参数       | 类型     | 必须   | 说明       |
|:---------|:-------|:-----|:---------|
| account  | string | true | 员工账号     |
| password | string | true | 密码，6-16位 |

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    | string | false | 用户令牌        |

##### 接口示例:

> http://localhost:8080/api/worker/login?account=987654&password=123456789

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGFpbXMiOnsiaWQiOm51bGwsImFjY291bnQiOiI5ODc2NTQifSwiZXhwIjoxNzMyNTIwMTU0fQ.TFi0PwvEqzg0VKIqWgkQJq-Df3ZVN8s30aASb6J1OJU"
}
```

#### 备注说明:

> 用户登录成功后系统下发`token`，后续请求需要在请求头`header`中加入名称为`Authorization`，值为下发的`token`

> 如果未登录响应码为401

### 2.2 获取登录员工信息

##### 接口功能:

> 用于获取登录员工信息

##### 请求路径:

> /api/worker/info

##### 请求方式:

> GET

##### 响应类型:

> JSON

##### 响应数据:

| 参数            | 类型     | 必须    | 说明          |
|:--------------|:-------|:------|:------------|
| code          | number | true  | 响应码,0成功,1失败 |
| message       | string | false | 提示信息        |
| \|data        | object | true  | 数据主体        |
| \|-employeeId | number | true  | 用户id        |
| \|-account    | String | true  | 用户账号        |
| \|-level      | number | true  | 用户等级        |

##### 接口示例:

> http://localhost:8080/api/worker/info

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": {
        "employeeId": 1,
        "account": "987654",
        "level": 2
    }
}
```

### 2.3 获取所有房间基础信息

##### 接口功能:

> 用于获取所有房间基础信息

##### 请求路径:

> /api/worker/rooms

##### 请求方式:

> GET

##### 响应类型:

> JSON

##### 响应数据:

| 参数             | 类型     | 必须    | 说明          |
|:---------------|:-------|:------|:------------|
| code           | number | true  | 响应码,0成功,1失败 |
| message        | string | false | 提示信息        |
| \|data         | object | true  | 数据主体        |
| \|-count       | number | true  | 房间数量        |
| \|-\|rooms     | list   | true  | 房间信息        |
| \|-\|-id       | number | true  | 房间号         |
| \|-\|-type     | string | true  | 房间类型        |
| \|-\|-state    | number | true  | 房间状态        |
| \|-\|-customer | string | true  | 房间所有者       |

##### 接口示例:

> http://localhost:8080/api/worker/rooms

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": {
        "count": 1,
        "rooms": [
            {
                "id": "0107",
                "type": "双人间",
                "status": 0,
                "customer": null
            }
        ]
    }
}
```

### 2.4 添加预定

##### 接口功能:

> 用于员工添加预定

##### 请求路径:

> /api/worker/newReservation

##### 请求方式:

> POST

##### 请求参数:

| 参数               | 类型     | 必须   | 说明     |
|:-----------------|:-------|:-----|:-------|
| customerId       | number | true | 客户名    |
| roomId           | string | true | 房间名    |
| numberOfGuests   | number | true | 客户数量   |
| expectedCheckin  | date   | true | 预期入住时间 |
| expectedCheckout | date   | true | 预期离开时间 |

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    | null   | false | null        |

##### 接口示例:

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": null
}
```

### 2.5 添加入住

##### 接口功能:

> 用于员工添加入住

##### 请求路径:

> /api/worker/newCheckin

##### 请求方式:

> POST

##### 请求体:

| 参数            | 类型     | 必须   | 说明     |
|:--------------|:-------|:-----|:-------|
| customerId    | string | true | 客户名    |
| roomId        | string | true | 房间名    |
| checkinTime   | date   | true | 预期入住时间 |
| departureTime | date   | true | 预期离开时间 |

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    | null   | false | null        |

##### 接口示例:

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": null
}
```
