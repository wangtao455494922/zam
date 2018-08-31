<%@ page language="java" contentType="text/html; charset=UTF8" pageEncoding="UTF8"%>
<%@ include file="/WEB-INF/jsp/include/taglib.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/include/head.jsp"%>
<script type="text/javascript">
	function query() {
		//执行关键词查询时清空过滤条件
		document.getElementById("catalogName").value="";
		document.getElementById("price").value="";
		//执行查询
		queryList();
	}
	function queryList() {
		$.ajax({
			url:"${path}/solr/good/list",
		   type:"POST",
	       data:$("form").serialize(),
	       dataType: "json",
	       success: function(data){
	    	$("#totalSizeSpan").text(data.totalSize);
	    	$("#totalPageI").text(data.totalPage);
	    	$("#totalPage").val(data.totalPage);
	    	var goodList = data.goodList;
	    	var ul =$("ul.list-h");
			var html="";	
			for (var int = 0; int < goodList.length; int++) {
				var good = goodList[int];
				html+="<li>";
				html+="<div class='lh-wrap'>";
				html+="<div class='p-img'>";
				html+="<img width='220' height='282' class='err-product' src='${path}/images/jd/"+good.picture+"'>";
				html+="</div>";
				html+="<div class='p-name'>"+good.name;
				html+="</div>";
				html+="<div class='p-price'>";
				html+="<strong>￥"+good.price+"</strong>";
				html+="</div>";
				html+="</div>";
				html+="</li>";
			}
			ul.html(html);
	       }
		});
	}
	function filter(key, value) {
		$("#start").val(1);
		$("#startI").text(1);
		
		document.getElementById(key).value=value;
		//执行查询
		queryList();
	}
	function sort() {
		var s = document.getElementById("sort").value; 
		if (s != "1") {
			s = "1";
		} else {
			s = "0";
		}
		document.getElementById("sort").value = s;
		//执行查询
		queryList();
	}
	function prev(){
		var start = $("#start").val();
		if (parseInt(start)==1) {
			alert("当前页为最小页！");
		} else {
			var newStrart = parseInt(start)-1;
			$("#start").val(newStrart);
			$("#startI").text(newStrart);
			queryList();
		}
	}
	function next(){
		var start = $("#start").val();
		var totalPage = $("#totalPage").val();
		if (parseInt(start)==parseInt(totalPage)) {
			alert("当前页为最大页！");
		} else {
			var newStrart = parseInt(start)+1;
			$("#start").val(newStrart);
			$("#startI").text(newStrart);
			queryList();
		}
	}
</script>
</head>
<body class="root61">
<div id="o-header-2013">
	<div class="w" id="header-2013">
		<!--logo end-->
		<div id="search-2013">
			<div class="i-search ld">
				<ul id="shelper" class="hide"></ul>
				<form id="actionForm">
				<div class="form">
					<input type="text" class="text" accesskey="s" name="name" id="key" value="${good.name}"
						autocomplete="off" onkeydown="javascript:if(event.keyCode==13) {query()}">
					<input type="button" value="搜索" class="button" onclick="query()">
				</div>
				<input type="hidden" name="catalogName" id="catalogName" value="${good.catalogName}"/> 
				<input type="hidden" name="price" id="price" value="${good.price}"/> 
				<input type="hidden" name="sort" id="sort" value="${good.sort}"/>
				<input type="hidden" name="start" id="start" value="${good.start}"/> 
				<input type="hidden" name="totalPage" id="totalPage" value="${good.totalPage}"/> 
				</form>
			</div>
		</div>
		<!--settleup end-->
	</div>
</div>
<div class="w main">
<div class="right-extra">
<div id="select" clstag="thirdtype|keycount|thirdtype|select" class="m">
	<div class="mc attrs">
		<div data-id="100001" class="brand-attr">
			<div class="attr">
				<div class="a-key">商品类别：</div>
				<div class="a-values">
					<div class="v-tabs">
						<div class="tabcon">
							<div>
								<a href="javascript:filter('catalogName', '幽默杂货')" >幽默杂货</a>
							</div>
							<div>
								<a href="javascript:filter('catalogName', '时尚卫浴')">时尚卫浴</a>
							</div>
							<div>
								<a href="javascript:filter('catalogName', '另类文体')">另类文体</a>
							</div>
							<div>
								<a href="javascript:filter('catalogName', '创意相架')">创意相架</a>
							</div>
							<div>
								<a href="javascript:filter('catalogName', '巧妙收纳')">巧妙收纳</a>
							</div>
							<div>
								<a href="javascript:filter('catalogName', '与钟不同')">与钟不同</a>
							</div>
							<div>
								<a href="javascript:filter('catalogName', '个性男人')">个性男人</a>
							</div>
							<div>
								<a href="javascript:filter('catalogName', '电脑周边')">电脑周边</a>
							</div>
							<div>
								<a href="javascript:filter('catalogName', '品质家电')">品质家电</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '品味茶杯')">品味茶杯</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '四季用品')">四季用品</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '健康宝宝')">健康宝宝</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '新潮美容')">新潮美容</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '产品配件')">产品配件</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '雅致灯饰')">雅致灯饰</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '阳光车饰')">阳光车饰</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '趣味纸抽')">趣味纸抽</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '布艺毛绒')">布艺毛绒</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '益智手工')">益智手工</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '环保餐具')">环保餐具</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '闪亮匙扣')">闪亮匙扣</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '手机饰品')">手机饰品</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '精品数码')">精品数码</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '理财钱罐')">理财钱罐</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '美味厨房')">美味厨房</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '保健按摩')">保健按摩</a>
							</div>						                      
							<div>						                      
								<a href="javascript:filter('catalogName', '魅力女人')">魅力女人</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div data-id="100002" class="prop-attrs">
			<div class="attr">
				<div class="a-key">价格：</div>
				<div class="a-values">
					<div class="v-fold">
						<ul class="f-list">
							<li><a href="javascript:filter('price','0-9')">0-9</a></li>
							<li><a href="javascript:filter('price','10-19')">10-19</a></li>
							<li><a href="javascript:filter('price','20-29')">20-29</a></li>
							<li><a href="javascript:filter('price','30-39')">30-39</a></li>
							<li><a href="javascript:filter('price','40-49')">40-49</a></li>
							<li><a href="javascript:filter('price','50-*')">50以上</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div id="filter">
	<div class="cls"></div>
	<div class="fore1">
		<dl class="order">
			<dt>排序：</dt>
			<dd>
				<a href="javascript:sort()">价格</a><b></b>
			</dd>
		</dl>
		<dl class="activity">
			<dd></dd>
		</dl>
		<div class="pagin pagin-m">
			<span class="text"><i id="startI">${good.start}</i>/<i id="totalPageI">${good.totalPage}</i></span>
			<a href="javascript:prev();" class="prev">上一页<b></b></a>
			<a href="javascript:next();" class="next">下一页<b></b></a>
		</div>
		<div class="total">
			<span>共<strong id="totalSizeSpan"> ${good.totalSize} </strong>个商品
			</span>
		</div>
		<span class="clr"></span>
	</div>
</div>
<!--商品列表开始-->
<div id="plist" class="m plist-n7 plist-n8 prebuy">
	<ul class="list-h">
		<c:forEach var="item" items="${good.goodList}">
		<li>
			<div class="lh-wrap">
				<div class="p-img">
					<img width="220" height="282" class="err-product" src="${path}/images/jd/${item.picture}">
				</div>
				<div class="p-name">
					${item.name}
				</div>
				<div class="p-price">
					<strong>￥ ${item.price}</strong>
				</div>
			</div>
		</li>
		</c:forEach>
	</ul>
</div>
<!--商品列表结束-->
</div>
</div>
</body>
</html>

