module.exports = {
  devServer: {
    port: 8080,
    proxy: {
      "/api": {
        target: "http://34.64.138.49:8080",
        changerOrigin: true,
        pathRewrite: {
          "^/api": "",
        },
      },
    },
  },
};
