define(["text!./template.html"], function(template) {
	return {
		template : template,
		style: '.el-switch.is-disabled .el-switch__core, .el-switch.is-disabled .el-switch__label {cursor: pointer;}',
		data : function() {
			return {
				loading: false,
		        currentPage1: 5,
		        currentPage2: 5,
		        currentPage3: 5,
		        currentPage4: 4,
				users: [
					{
						username: 'maomoa',
						accessKey: 'f4fdg5d5fgfd4g4fdgd4f5gdfd54f4d',
						secondaryDomain: 'org.coma.com',
						localPort: 80,
						disable: true,
						createTime: '2020-02-03 12:02:10'
					},
					{
						username: 'tiantia',
						accessKey: 'dg4f1gs45d4s4f54dd5f4ds1d4f5dsf',
						secondaryDomain: 'tiantia.coma.com',
						localPort: 80,
						disable: true,
						createTime: '2020-12-03 05:07:59'
					},
					{
						username: 'wenjuan',
						accessKey: 'cv14vaada8w4a4dsd5as5x1c54s54d5',
						secondaryDomain: 'wenjuan.coma.com',
						localPort: 80,
						disable: true,
						createTime: '2020-07-25 10:36:22'
					}
				]
			}
		},
    	created:function(){
    		
    	},
        methods: {
        	// 格式化时间
            formatDate: function(row) {
                //return new Date(row.date).toLocaleDateString();
            	return row.createTime;
            },
        	// 监听排序
        	handleSortChange: function(sortWay) {
        		console.info("排序："+sortWay);
        	},
        	// 监听选择列
            handleSelectionChange: function(val) {
            	console.info("已选择列");
            	console.info(val);
            },
            // 监听是否启用开关
            handleDisable: function(scope) {
            	scope.row.disable = !scope.row.disable;
            	console.info(scope);
            },
            // 监听搜索按钮
            handleSearch: function() {
            	
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
              },
              handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
              }
        }
	}
});