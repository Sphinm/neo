// https://github.com/arackaf/customize-cra/blob/HEAD/api.md#addlessloaderloaderoptions
const {
  override,
  fixBabelImports,
  addWebpackPlugin,
  overrideDevServer,
  addWebpackExternals, // cdn 引入
  addWebpackAlias,
  addDecoratorsLegacy,
} = require('customize-cra')

const path = require('path')
const apiMocker = require('mocker-api')
const resolve = dir => path.resolve(__dirname, dir)
const AntdDayjsWebpackPlugin = require('antd-dayjs-webpack-plugin')

const devProxy = () => config => {
  // config.before = app => {
  //   apiMocker(app, resolve('src/mock/index.js'))
  // }
  config.proxy = {
    '/api': {
      target: 'http://localhost:8008/',
      changeOrigin: true,
    },
  }
  return config
}

const stylus = () => config => {
  const stylusLoader = {
    test: /\.styl$/,
    use: [
      {
        loader: 'style-loader',
      },
      {
        loader: 'css-loader',
        options: {
          modules: {
            mode: 'local',
            // 样式名规则配置
            localIdentName: '[local]--[hash:base64:5]',
          },
        },
      },
      {
        loader: 'stylus-loader',
      },
    ],
  }
  const oneOf = config.module.rules.find(rule => rule.oneOf).oneOf
  oneOf.unshift(stylusLoader)
  return config
}

module.exports = {
  webpack: override(
    fixBabelImports('import', {
      libraryName: 'antd',
      libraryDirectory: 'es',
      style: 'css',
    }),
    addWebpackPlugin(new AntdDayjsWebpackPlugin()),
    addWebpackExternals({
      React: 'React',
    }),
    addWebpackAlias({
      ['@']: path.resolve(__dirname, 'src'),
    }),
    addDecoratorsLegacy(), // 支持装饰器
    stylus(),
  ),
  devServer: overrideDevServer(devProxy()),
}
