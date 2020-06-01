define(["text!./template.html"], function(template) {
	return {
		template : template,
		data : function() {
			return {
				node:{
					name:"测试数据"
				},
				name: '我是name'
			}
		},
    	created:function(){
    		
    	},
        methods: {
        	
        }
	}
});