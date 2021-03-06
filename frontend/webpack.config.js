const path = require('path');
const HTMLWebpackPlugin = require('html-webpack-plugin');

const rootDir = path.resolve(__dirname, '../');
const HTMLWebpackPluginConfig = new HTMLWebpackPlugin({
	template: path.resolve(__dirname, 'app/index.html'),
	filename: 'index.html',
	inject: 'body'
});

module.exports = {
	entry: path.resolve(__dirname, 'app/index.js'),
	module: {
		rules: [
			{
				test: /\.js$/,
				exclude: /node_modules/,
				loader: 'babel-loader'
			},
			{
				test: /\.css$/,
				loader: 'style-loader!css-loader?modules&importLoaders=1&localIdentName=[name]__[local]___[hash:base64:5]'
			}
		]
	},
	output: {
		filename: 'transformed.js',
		path: path.resolve(rootDir, 'src/main/resources/static'),
	},
	plugins: [HTMLWebpackPluginConfig]
};