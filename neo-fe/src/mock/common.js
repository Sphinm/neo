const Mock = require('mockjs')
const Login = {
  'POST /api/login': Mock.mock({
    code: 'SUCCESS',
    data: '',
    message: 'SUCCESS',
  }),
  'GET /api/logout': Mock.mock({
    code: 'SUCCESS',
    data: '',
    message: 'SUCCESS',
  }),
  'GET /api/me': Mock.mock({
    code: 'SUCCESS',
    data: {
      role: 'ADMIN',
    },
    message: 'SUCCESS',
  }),
}

module.exports = Login
