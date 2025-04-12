
resource "aws_lb_target_group" "storage_service_tg" {
  name     = "storage-service-tg"
  port     = 8080
  protocol = "HTTP"
  vpc_id   = var.vpc_id
  target_type = "ip"
  health_check {
    path = "/storage-service/health"
  }
}

resource "aws_lb_listener_rule" "storage_service_rule" {
  listener_arn = data.aws_lb_listener.lb-listener.arn
  priority     = 90

  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.storage_service_tg.arn
  }

  condition {
    path_pattern {
      values = ["/storage-service/*"]
    }
  }
}
