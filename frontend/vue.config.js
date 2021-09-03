module.exports = {
  devServer: {
    proxy: "http://localhost:8080",
    // proxy: {
    //   "/api": {
    //     target: "http://localhost:8080",
    //     ws: true,
    //     changeOrigin: true,
    //   },
    // },
  },
};
