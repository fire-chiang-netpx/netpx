define(function(require){
	var Vue = require("vue");
	var VueRouter = require("vue-router");
	Vue.use(VueRouter);
	
	return new VueRouter({
		routes:[
            {
            	// 默认跳转路由
				path: "/",
				redirect: "/index"
		    },{
		        path: "/index",
		        // Vue.extend(require("views/test/index"))
			    component: function(resolve){
			        return requirejs(['views/index/index'],resolve);	
			    },
			    children: [
			    	{
				        path: "user-manager",
				        // Vue.extend(require("views/test/index"))
					    component: function(resolve){
					        return requirejs(['views/user-manager/index'],resolve);	
					    }
			    	}
			    ]
			}
		]
	});
});