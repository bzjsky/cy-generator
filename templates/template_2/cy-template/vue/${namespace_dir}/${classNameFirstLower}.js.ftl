<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
import request from '@/api/request'
import requestUrl from '@/api/requestUrl'
import requestParam from '@/api/requestParam'
import isInteger from 'lodash/isInteger'

// 获取${table.remarks}列表
export function listPage (params) {
  return request({
    url: requestUrl('/${classNameLower}/queryListPage'),
    method: 'get',
    params: requestParam(params, 'get')
  })
}

// 获取${table.remarks}信息
export function info (id) {
  return request({
    url: requestUrl('/${classNameLower}/getById' + (isInteger(id) ? '?id=' + id : '')),
    method: 'get',
    params: requestParam({}, 'get')
  })
}

// 添加${table.remarks}
export function add (params) {
  return request({
    url: requestUrl('/${classNameLower}/save'),
    method: 'post',
    data: requestParam(params)
  })
}

// 修改${table.remarks}
export function update (params) {
  return request({
    url: requestUrl('/${classNameLower}/modify'),
    method: 'post',
    data: requestParam(params)
  })
}

// 删除${table.remarks}
export function del (params) {
  return request({
    url: requestUrl('/${classNameLower}/removeBatch'),
    method: 'post',
    data: requestParam(params, 'post', false)
  })
}
