<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
  </head>
  <body>
    <script>
      function rec(n) {
        if (n === 0) return;

        rec(n - 1);
        console.log(n + ' ');
      }

      function solution(n) {
        rec(n);
      }

      console.log(solution(3));
    </script>
  </body>
</html>
