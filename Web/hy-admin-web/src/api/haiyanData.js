import request from '@/utils/request'
import { stringify } from 'query-string'

export function getWorkOrderList(pageNo, pageSize, data) {
  const search = Object.assign({ pageNo, pageSize })
  return request({
    url: `/statistic/api/v1/haiyan/workOrder/list?${stringify(search)}`,
    method: 'post',
    data: data
  })
}

export function getWorkOrderDetail(workId) {
  const data = Object.assign({ workId })
  return request({
    url: `/statistic/api/v1/haiyan/workOrder/detail?${stringify(data)}`,
    method: 'get',
  })
}

export function workOrderSubmit(data) {
  return request({
    url: '/statistic/api/v1/haiyan/workOrder/submit',
    method: 'post',
    data:data
  })
}
