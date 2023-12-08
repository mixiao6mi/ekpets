## modules
 包含项目所有模块，模块介绍[文档](/modules/README.md)
## 依赖库

  项目所依赖的所有库可以在config/[libraries.gradle](config/libraries.gradle)下配置。

  如果某个模块想添加某个依赖，只需要在libMap下创建创建:

  "模块名":["库1","库2"]

   所有库都用implementation进行依赖


## 网络

   项目主要使用协程进行网络请求，[文档](modules/module-net/README.md)


## 路由

   项目使用ARouter进行页面跳转和模块间交互，[使用文档](modules/ROUTE.md)