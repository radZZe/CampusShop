package com.mimoza_app.notes.campusshop.models

data class ShopItem(val name: String = "",
                    val price: String = "",
                    val description: String = "",
                    val building: String = "",
                    val category: String = "",
                    val picture: String = placeholderBytecode,
                    val itemId: String = "")
{
    companion object{
        const val placeholderBytecode = "iVBORw0KGgoAAAANSUhEUgAAAeAAAAHgCAYAAAB91L6VAAAACXBIWXMAABJ0AAASdAHeZh94AAASFklEQVR4nO3da1caZ9uA4WsAwU00wSTdJE+Spv3/P6lP1dRm1yqIWxiY90NrV988SSoiXDIcx1r90qDcy8Xi5J6ZiyneH/WrAAAWqpG9AABYRQIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAla2QuAf6qqKq6uruJ0MIiLi4sYDocxHo+zl8USK4oi1tbWorO+HltbW7G5uRmtlrc+8nkVkq6qqphMJjE4OYmP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/1yr1ZrqAyPchgCzMO1OJxqNRkxueGi5qqo4PDz0RshMJpNJVFU11c9sbGzYATN3AszCtNvtWF9fj/Pz86l+bppzd3AXtn2vOAvguB4LUxRFPOp2s5cBX9Vut2NzczN7GawAAWahHj16FO12O3sZ8EVPnjxx2oOFEGAWqtVqxdOnT7OXAZ+1vr4e3d3d7GWwIgSYhSqKInYfP3aOjXun0WjEs+fPo9VyaQyLIcAsXKPRiOfPn089lgTz9O1338WDBw+yl8EKEWBSdDqd+PGnn2J9fT17Kay4oiji2bNn8fTpU6NHLJQAk6bdbscPr1/HjsPRJGm1WvGfF/+JJ+JLguL9UX+6CXW4Y1VVxdHRUXz88CGurq6yl8MKaDQasbOzE99//32stdviSwoB5l6oqirGZRn9k5PoHR/HxcWFL+DgThVFEe12O7Z3dmK3243O+rqvOCWVAHPvVFUVZVnGcDiM4XAYk8k4wquUWyqKIlpra9Fut6Pdbosu94YAA0ACHwUBIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0ACAQaABAIMAAkEGAASCDAAJBBgAEggwACQQIABIIEAA0CC/wOvxooKGozIEQAAAABJRU5ErkJggg=="
        const val UNDEFINED_ID = -1
    }
}