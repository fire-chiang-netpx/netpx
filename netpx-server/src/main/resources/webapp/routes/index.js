define(function(require){
	var Vue = require("vue");
	var VueRouter = require("vue-router");
	Vue.use(VueRouter);
	
	return new VueRouter({
		routes:[{
	        path: "/config",
		    component: resolve => requirejs(['views/test/index'],resolve) //Vue.extend(require("views/test/index"))
		}]
	});
});