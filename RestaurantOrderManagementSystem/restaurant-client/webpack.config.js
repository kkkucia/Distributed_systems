const path = require('path');

module.exports = {
  mode: 'production',
  entry: './clientGrpc.js',
  output: {
    filename: 'bundle.js',
    path: path.resolve(__dirname, 'dist'),
  },
};
