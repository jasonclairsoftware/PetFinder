import { HttpInterceptorFn } from '@angular/common/http';

export const tokenInterceptor: HttpInterceptorFn = (req, next) => {
  /*
  const token = localStorage.getItem("token");
  const newReq = req.clone({
    setHeaders: {
      Authorization: `Bearer ${token}`
    }
  });
  return next(newReq);
  */

  const token = localStorage.getItem("token");

  // 1. Check if the token exists before cloning the request
  if (token) {
    const newReq = req.clone({
      setHeaders: {
        // 2. ONLY attach the header if the token is present
        Authorization: `Bearer ${token}`
      }
    });
    return next(newReq);
  }

  // 3. If no token, allow the original request to proceed (for public endpoints)
  return next(req);
};
