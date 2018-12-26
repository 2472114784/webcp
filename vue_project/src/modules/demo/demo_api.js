export default {
  banner: {
    method: 'get',
    url: '/banner',
    data: {}
  },
  testGet: {
    url: '/testAPI/get',
    method: 'get'
  },
  testPost: {url: '/testAPI/post'},
  testErr: {url: '/err', method: 'post'}
}
