import request from '@/utils/request'

export function sendMessageToMeiban(data) {
  return request({
    url: '/statistic/api/v1/meiban/message',
    method: 'post',
    data: data
  })
}
