package com.nttdata.dockerized.postgresql.service;

import com.nttdata.dockerized.postgresql.exceptions.BadRequestException;
import com.nttdata.dockerized.postgresql.exceptions.InternalServerErrorException;
import com.nttdata.dockerized.postgresql.exceptions.ResourceNotFoundException;
import com.nttdata.dockerized.postgresql.model.entity.Categoria;
import com.nttdata.dockerized.postgresql.model.entity.Producto;
import com.nttdata.dockerized.postgresql.repository.CategoriaRepository;
import com.nttdata.dockerized.postgresql.repository.ProductoRepository;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService{

    private final ProductoRepository productoRepository;

    private final CategoriaRepository categoriaRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Producto guardarProducto(Long idCategoria, Producto producto) {

        // Validar datos obligatorios del producto
        if (producto.getNombre() == null || producto.getNombre().isBlank()) {
            throw new BadRequestException("El nombre del producto es obligatorio");
        }

        if (producto.getPrecio() == null || producto.getPrecio().doubleValue() <= 0) {
            throw new BadRequestException("El precio del producto debe ser mayor a 0");
        }

        // Buscar categoria
        Categoria categoria = categoriaRepository.findById(idCategoria).orElseThrow(
                () -> new ResourceNotFoundException("El id de la categoría indicada no existe")
        );

        producto.setCategoria(categoria);

        try {
            return productoRepository.save(producto);
        } catch (DataAccessException e) {
            throw new InternalServerErrorException("Error al guardar el producto en la base de datos");
        }

    }

}
