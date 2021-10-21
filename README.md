# BlogProjece
前后端分离项目
	/Server  服务器端的源目录
	/web     前端的源目录
	
	/web/config/dev.env.js     #环境配置,通过修改BASE_API 来修改访问的后端地址
	/web/config/prod.env.js    #生产环境配置 BASE_API是对应自己的域名
	/web/config/static      #存放前端项目用到的图片资源
	
#前端源码
	/web/config/src
	/web/config/src/api  后端访问接口的定义
	/web/config/src/api/article.js 里面的function getArticles 为例

#首页显示的文章
#该接口访问的就是 后端的articles 接口  访问方式：post 访问数据就是data:里面的
export function getArticles(query, page) {
  return request({
    url: '/articles',
    method: 'post',
    data: {
      page: page.pageNumber,
      pageSize: page.pageSize,
      name: page.name,
      sort: page.sort,
      year: query.year,
      month: query.month,
      tagId: query.tagId,
      categoryId: query.categoryId
    }
  })
}



#热门文章
#访问的是/articles/hot接口 访问方式是 post  
articlesexport function getHotArtices() {
  return request({
    url: '/articles/hot',
    method: 'post'
  })
}



/web/config/src/api/login.js
#获取用户token   前端通过这个获取token 然后传到headers 里面 后端也可以通过这个headers获取到用户的token
export function getUserInfo(token) {
  return request({
    headers: {'Authorization': token},
    url: '/users/currentUser',
    method: 'get'
  })
}




/web/config/src/views/Home.vue
#前端的主页面


