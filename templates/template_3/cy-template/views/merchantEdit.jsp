
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../../common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>" />
</head>
<body>
	<div class="blank5"></div>
	<div class="dataDiv">
		<div class="dataDivHeader"></div>
		<form id="form2" class="easyui-form" method="post">
			<input type="hidden" name="id" value="${pojo.id}"/>
			<input type="hidden" id="reviewStatus" name="reviewStatus" value="${dto.reviewStatus}"/>
			<table cellspacing="0" cellpadding="0" class="editTable">
				<tr>
				    <th>商品名称：</th>
				    <td>${dto.proName}</td>
				    <th>商品编号：</th>
				    <td>${dto.proNo}</td>
				</tr>
				<tr>
				    <th>商品分类：</th>
				    <td>${dto.classifyId}</td>
				    <th>尺寸：</th>
				    <td>${dto.size}</td>
				</tr>
				<tr>
				    <th>重量：</th>
				    <td>${dto.weight}</td>
				    <th>材质：</th>
				    <td>${dto.materialQuality}</td>
				</tr>
				<tr>
				    <th>风格：</th>
				    <td>${dto.styleId}</td>
				    <th>产地：</th>
				    <td>${dto.place}</td>
				</tr>
				<tr>
				    <th>销售价格：</th>
				    <td>${dto.salePrice}</td>
				    <th>市场价格：</th>
				    <td>${dto.price}</td>
				</tr>
				<tr>
				    <th>商家：</th>
				    <td>${dto.merchantName}</td>
				    <th>审核状态：</th>
				    <td>${dto.reviewStatusName}</td>
				</tr>
				<tr>
				    <th>推广费用：</th>
				    <td>${dto.geneExp}</td>
				    <th>商品访问统计：</th>
				    <td>${dto.proVistorCount}</td>
				</tr>
				<tr>
				    <th>上下架时间：</th>
				    <td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dto.shelveTime}" /></td>
				    <th>上下架状态：</th>
				    <td>${dto.shelveStatusName}</td>
				</tr>
				<tr>
					<th>创建时间：</th>
					<td colspan="3"><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dto.createTime}" /></td>
				</tr>
				<tr>
				    <th>商品介绍：</th>
				    <td colspan="3">${dto.proDesc}</td>
				</tr>
				<tr>
				    <th>产品图片：</th>
				    <td colspan="3">
				    	<c:forEach items="${dto.proImageList}" var="proImage">
				    		<img src="${dto.imageUrl}${proImage.proImgUrl}?w=100&h=100" heigth="100px" width="100px" onclick ="window.open('${dto.imageUrl}${proImage.proImgUrl}');"/>
				    	</c:forEach>
				    </td>
				</tr>
				<tr>
				    <th>审核备注：</th>
				    <td colspan="3"><textarea name="remark" rows="3" cols="60">${dto.remark}</textarea></td>
				</tr>
			</table>
		</form>
		<div class="dataDivFooter"></div>
	</div>
	<div class="blank5"></div>
</body>
</html>