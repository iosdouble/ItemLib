import request from '@/utils/request'

export function getUserStas(data) {
  return request({
    url: '/statistic/api/v1/user/analyse',
    method: 'post',
    data: data
  })
}

export function getUserRatio(data) {
  return request({
    url: '/statistic/api/v1/user/ratio',
    method: 'post',
    data: data
  })
}

export function getWorkAnalyse(data) {
  return request({
    url: '/statistic/api/v1/work/analyse',
    method: 'post',
    data: data
  })
}

export function getWorkRatio(data) {
  return request({
    url: '/statistic/api/v1/work/ratio',
    method: 'post',
    data: data
  })
}

export function getWorkDeliverAnalyse(data) {
  return request({
    url: '/statistic/api/v1/work/deliver/analyse',
    method: 'post',
    data: data
  })
}

export function getWorkDeliverRatio(data) {
  return request({
    url: '/statistic/api/v1/work/deliver/ratio',
    method: 'post',
    data: data
  })
}

export function getWorkTimeAnalyse(data) {
  return request({
    url: '/statistic/api/v1/work/time/analyse',
    method: 'post',
    data: data
  })
}

export function getWorkTimeRatio(data) {
  return request({
    url: '/statistic/api/v1/work/time/ratio',
    method: 'post',
    data: data
  })
}

export function getWorkTimeAnalyseData(data) {
  return request({
    url: '/statistic/api/v1/work/time/analyse/data',
    method: 'post',
    data: data
  })
}

export function getQueryCallAnalyse(data) {
  return request({
    url: '/statistic/api/v1/query/call/analyse',
    method: 'post',
    data: data
  })
}

export function getQueryCallRatio(data) {
  return request({
    url: '/statistic/api/v1/query/call/ratio',
    method: 'post',
    data: data
  })
}
