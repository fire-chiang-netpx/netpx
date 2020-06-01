define(['text!./template.html'],function(template){
	return {
		template: template,
		data: function(){
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
		},
    	created:function(){
    		
    	},
        methods: {
        	showSettingModal: function(){
        		alert("showSettingModal");
        	},
        	goGithub: function(){
        		alert("goGithub");
        	},
        	goBook: function(){
        		alert("goBook");
        	},
        	trigger: function(){
        		alert("trigger");
        	}
        },
        watch: {
            headerFixed: function() {
              if (!this.headerFixed) {
                this.siderFixed = false;
              }
            }
	}
	}
});