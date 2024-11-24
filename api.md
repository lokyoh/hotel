# 酒店管理系统接口文档

## 1 用户接口

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

> http://server.lokyoh.com:8080/api/user/register?&

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

> http://server.lokyoh.com:8080/api/user/login?&

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

### 1.3 获取个人信息x

##### 接口功能:

> 用于用户获取个人信息

##### 请求路径:

> /api/user/info

##### 请求方式:

> Get

##### 响应类型:

> JSON

##### 响应数据:

| 参数                | 类型     | 必须    | 说明          |
|:------------------|:-------|:------|:------------|
| code              | number | true  | 响应码,0成功,1失败 |
| message           | string | false | 提示信息        |
| \|data            | object | true  | 数据体         |
| \|-customerId     | number | true  | 用户id        |
| \|-identification | string | true  | 用户身份证号      |
| \|-cname          | string | true  | 用户名         |
| \|-caddress       | string | true  | 用户居住地址      |
| \|-phone          | string | true  | 用户电话        |
| \|-membership     | string | true  | 用户会员等级      |
| \|-totalSpent     | number | true  | 用户消费        |

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": {
        "customerId": "2",
        "identification": "330311200410010010",
        "cname": "黄振奇",
        "gender": "男",
        "caddress": "江苏省扬州市AA街道",
        "phone": "18817132011",
        "membership": "二级会员",
        "totalSpent": 1100.00
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
| data    | string | true  | 用户令牌        |

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

### 2.6 获取用户ID

##### 接口功能:

> 用于获取并验证用户信息

##### 请求路径:

> /api/worker/userId

##### 请求方式:

> POST

##### 请求参数:

| 参数             | 类型     | 必须   | 说明   |
|:---------------|:-------|:-----|:-----|
| cname          | string | true | 用户名  |
| identification | string | true | 身份证号 |

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    | number | true  | 用户ID        |

##### 接口示例:

> http://localhost:8080/api/worker/userId?cname=无邪&identification=230064197807010210

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 10
}
```

### 2.7 获取指定房间信息

##### 接口功能:

> 用于获取指定房间信息

##### 请求路径:

> /api/worker/roomInfo

##### 请求方式:

> POST

##### 请求参数:

| 参数     | 类型     | 必须   | 说明  |
|:-------|:-------|:-----|:----|
| roomId | string | true | 房间号 |

##### 响应类型:

> JSON

##### 响应数据:

| 参数            | 类型        | 必须    | 说明          |
|:--------------|:----------|:------|:------------|
| code          | number    | true  | 响应码,0成功,1失败 |
| message       | string    | false | 提示信息        |
| \|data        | object    | true  | 数据体         |
| \|-roomID     | string    | true  | 房间id        |
| \|-type       | string    | true  | 房间类型        |
| \|-startTime  | date      | true  | 开始时间        |
| \|-endTime    | date      | true  | 结束时间        |
| \|-customerId | number    | true  | 开房用户id      |
| \|-resident   | list(str) | true  | 住户列表        |

##### 接口示例:

> http://localhost:8080/api/worker/userId?roomId=0107

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": {
        "roomID": "0107",
        "type": "双人间",
        "startTime": "2024-11-22",
        "endTime": "2024-11-23",
        "customerId": 2,
        "resident":[
            "黄振奇",
            "王佳妮"
        ]
    }
}
```

### 2.8 获取个人信息

##### 接口功能:

> 用于员工获取用户信息

##### 请求路径:

> /api/worker/userInfo

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|
| customerId | number | true | 要查询的用户id |

##### 响应类型:

> JSON

##### 响应数据:

| 参数                | 类型     | 必须    | 说明          |
|:------------------|:-------|:------|:------------|
| code              | number | true  | 响应码,0成功,1失败 |
| message           | string | false | 提示信息        |
| \|data            | object | true  | 数据体         |
| \|-customerId     | number | true  | 用户id        |
| \|-identification | string | true  | 用户身份证号      |
| \|-cname          | string | true  | 用户名         |
| \|-caddress       | string | true  | 用户居住地址      |
| \|-phone          | string | true  | 用户电话        |
| \|-membership     | string | true  | 用户会员等级      |
| \|-totalSpent     | number | true  | 用户消费        |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/userInfo?customerId=2

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": {
        "customerId": "2",
        "identification": "330311200410010010",
        "cname": "黄振奇",
        "gender": "男",
        "caddress": "江苏省扬州市AA街道",
        "phone": "18817132011",
        "membership": "二级会员",
        "totalSpent": 1100.00
    }
}
```

### 2.9 修改预定信息x

##### 接口功能:

> 

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.10 取消预定x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.11 修改入住信息x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.12 添加客人x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.13 删除客人x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.14 获取换房信息x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.15 换房x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.16 获取退房信息x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.17 退房x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.18 获取预定表x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.19 获取入住表x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.20 获取客户表x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.21 添加客户x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.22 修改客户信息x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.23 注册会员x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.24 获取员工表x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.25 添加员工x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.26 修改员工信息x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.27 获取房间表x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.28 添加房间表x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.29 修改房间信息x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.30 删除员工x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```

### 2.31 删除房间x

##### 接口功能:

>

##### 请求路径:

> /api/worker/

##### 请求方式:

> POST

##### 请求参数:

| 参数         | 类型     | 必须   | 说明       |
|:-----------|:-------|:-----|:---------|

##### 响应类型:

> JSON

##### 响应数据:

| 参数      | 类型     | 必须    | 说明          |
|:--------|:-------|:------|:------------|
| code    | number | true  | 响应码,0成功,1失败 |
| message | string | false | 提示信息        |
| data    |        |       |             |

##### 接口示例:

> http://server.lokyoh.com:8080/api/worker/

返回:

```
{
    "code": 1,
    "message": "操作成功",
    "data": 
}
```
