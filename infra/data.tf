data "aws_lb_listener" "lb-listener" {
  load_balancer_arn = var.alb_listener_arn
  port              = 80
}

data "aws_ecs_cluster" "video_cluster" {
  cluster_name = var.video_cluster_name
}