require.config({
    baseUrl:"/",
    paths: {
        'text': 'assets/require/text',
	    'vue' : 'assets/vue/vue.min',
	    'HeyUI': 'assets/heyui/heyui',
	    'vue-router':'assets/vue/vue-router.min'
    },
    // 添加统一参数
    /*urlArgs:function(id, url) {
        return (url.indexOf('?') === -1 ? '?' : '&') + '';
    },*/
    // 项目引导模块
    deps : ['main'] 
});
define("main",function(require) {
    var text = require("text");
	var Vue = require("vue");
    var heyui = require("HeyUI");
	Vue.use(heyui);
	Vue.component("app-header",require("views/home/header/index"));
	//Vue.component("headerbar",require("components/headerbar/index"));
	//Vue.component("collapse-transition",require("components/collapse-transition"));
	new Vue({
		el:"#app",
		router:require("routes/index"),
		data:function(){
			
			return {
				
				
				searchText: '',
				infoMenu: [
				    { key: 'info', title: '个人信息', icon: 'h-icon-user' },
				    { key: 'logout', title: '退出登录', icon: 'h-icon-outbox' }
				],
				 headerFixed: false,
			      siderFixed: false,
			      siderCollapsed: false,
			      menuDatas: [
			        { title: '首页', key: 'welcome', icon: 'h-icon-home' },
			        { title: '查询', key: 'search', icon: 'h-icon-search' },
			        { title: '收藏', key: 'favor', icon: 'h-icon-star', count: 100, children: [{ title: '收藏-1', key: 'favor2-1' }] },
			        { title: '任务', icon: 'h-icon-task', key: 'task' }
			      ],
			      datas: [
			        { icon: 'h-icon-home' },
			        { title: 'Component', icon: 'h-icon-complete', route: { name: 'Component' } },
			        { title: 'Breadcrumb', icon: 'h-icon-star' }
			      ],
			      data: [
			             {
			               title: '首页',
			               key: 'welcome',
			               icon: 'h-icon-home'
			             },
			             {
			               title: '查询',
			               key: 'search',
			               icon: 'h-icon-search'
			             }]
			}
		}
	});
});